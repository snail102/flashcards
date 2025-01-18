package ru.anydevprojects.flashcards.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ApkgFileManager(private val applicationContext: Context) {

    // TODO вынести в DI
    private val json = Json {
        ignoreUnknownKeys = true
    }
    suspend fun extractFile(filePath: String): Result<String> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val fileNameWithExtension = filePath.substringAfterLast("/")
            val fileName = fileNameWithExtension.substringBefore(".")
            val outputDir = "${applicationContext.cacheDir.path}/$fileName"
            val zipFile = ZipFile(filePath)
            zipFile.entries().asSequence().forEach { entry ->
                val file = File(outputDir, entry.name)
                if (entry.isDirectory) {
                    file.mkdirs()
                } else {
                    file.parentFile?.mkdirs()
                    zipFile.getInputStream(entry).use { input ->
                        file.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
            }
            outputDir
        }
    }

    suspend fun getDataFromDatabaseFile(collectionPath: String): Result<CollectionDatabaseModel> =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val cardsList = mutableListOf<CardsAnkiModel>()
                val notesList = mutableListOf<NotesAnkiModel>()
                val decksList = mutableListOf<DeckAnkiModel>()
                val dbFile = File("$collectionPath/collection.anki21")
                if (!dbFile.exists()) {
                    throw IllegalArgumentException(
                        "Файл базы данных не найден: $collectionPath/collection.anki21"
                    )
                }
                val db = SQLiteDatabase.openDatabase(
                    dbFile.path,
                    null,
                    SQLiteDatabase.OPEN_READONLY
                )

                val cardsCursor = db.rawQuery("SELECT * FROM cards", null)
                cardsCursor.use { cursor ->
                    if (cursor.moveToFirst()) {
                        do {
                            val card = CardsAnkiModel(
                                id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                                nid = cursor.getLong(cursor.getColumnIndexOrThrow("nid")),
                                did = cursor.getLong(cursor.getColumnIndexOrThrow("did")),
                                ord = cursor.getInt(cursor.getColumnIndexOrThrow("ord")),
                                mod = cursor.getLong(cursor.getColumnIndexOrThrow("mod")),
                                usn = cursor.getInt(cursor.getColumnIndexOrThrow("usn")),
                                type = cursor.getInt(cursor.getColumnIndexOrThrow("type")),
                                queue = cursor.getInt(cursor.getColumnIndexOrThrow("queue")),
                                due = cursor.getLong(cursor.getColumnIndexOrThrow("due")),
                                ivl = cursor.getInt(cursor.getColumnIndexOrThrow("ivl")),
                                factor = cursor.getInt(cursor.getColumnIndexOrThrow("factor")),
                                reps = cursor.getInt(cursor.getColumnIndexOrThrow("reps")),
                                lapses = cursor.getInt(cursor.getColumnIndexOrThrow("lapses")),
                                left = cursor.getInt(cursor.getColumnIndexOrThrow("left")),
                                odue = cursor.getLong(cursor.getColumnIndexOrThrow("odue")),
                                odid = cursor.getLong(cursor.getColumnIndexOrThrow("odid"))
                            )
                            cardsList.add(card)
                        } while (cursor.moveToNext())
                    }
                }

                val notesCursor = db.rawQuery("SELECT * FROM notes", null)
                notesCursor.use { cursor ->
                    if (cursor.moveToFirst()) {
                        do {
                            val note = NotesAnkiModel(
                                id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                                guid = cursor.getString(cursor.getColumnIndexOrThrow("guid")),
                                mid = cursor.getLong(cursor.getColumnIndexOrThrow("mid")),
                                mod = cursor.getLong(cursor.getColumnIndexOrThrow("mod")),
                                usn = cursor.getInt(cursor.getColumnIndexOrThrow("usn")),
                                tags = cursor.getString(cursor.getColumnIndexOrThrow("tags")),
                                flds = cursor.getString(cursor.getColumnIndexOrThrow("flds")),
                                sfld = cursor.getString(cursor.getColumnIndexOrThrow("sfld")),
                                csum = cursor.getLong(cursor.getColumnIndexOrThrow("csum")),
                                flags = cursor.getInt(cursor.getColumnIndexOrThrow("flags")),
                                data = cursor.getString(cursor.getColumnIndexOrThrow("data"))
                            )
                            notesList.add(note)
                        } while (cursor.moveToNext())
                    }
                }

                val colCursor = db.rawQuery("SELECT * FROM col", null)
                colCursor.use { cursor ->
                    if (cursor.moveToFirst()) {
                        do {
                            val decksJson =
                                cursor.getString(cursor.getColumnIndexOrThrow("decks"))
                            val decksMap = json.decodeFromString<Map<String, DeckAnkiModel>>(
                                decksJson
                            )

                            for ((key, value) in decksMap) {
                                if (key != "1") {
                                    decksList.add(value)
                                }
                            }
                        } while (cursor.moveToNext())
                    }
                }

                CollectionDatabaseModel(
                    cards = cardsList,
                    notes = notesList,
                    decks = decksList
                )
            }
        }

    suspend fun copyMediaFiles(directoryPath: String): Result<String> =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val directory = File(directoryPath)

                if (!directory.exists() || !directory.isDirectory) {
                    throw IllegalArgumentException(
                        "The path $directoryPath is not a valid directory."
                    )
                }

                val collectionsDir = File(applicationContext.filesDir, "collections")
                if (!collectionsDir.exists()) {
                    collectionsDir.mkdir()
                }

                val outputDir = File(collectionsDir, directory.name)
                if (!outputDir.exists()) {
                    outputDir.mkdir()
                }

                val mediaFileForConvertNaming =
                    directory.listFiles()?.find { it.name == "media" }
                        ?: throw IllegalArgumentException(
                            "The media file is missing."
                        )

                val jsonString = mediaFileForConvertNaming.readText()

                val fileMapping = Json.decodeFromString<Map<String, String>>(jsonString)

                val mediaFiles =
                    directory.listFiles()?.filter { filterCondition(it.nameWithoutExtension) }
                        ?: emptyList()

                Log.d("copyFile", "media files $mediaFiles")

                mediaFiles.forEach {
                    applicationContext.contentResolver.openInputStream(it.toUri())
                        ?.use { inputStream ->
                            Log.d("copyFile", "start copy file ${it.name}")
                            val tempFile = File(outputDir, fileMapping[it.name] ?: it.name)
                            tempFile.createNewFile()
                            FileOutputStream(tempFile).use { oStream ->
                                inputStream.copyTo(oStream)
                            }
                        }
                }

                outputDir.path
            }
        }

    private fun filterCondition(fileName: String): Boolean = when (fileName) {
        "meta", "collection", "media" -> false
        else -> true
    }
}
