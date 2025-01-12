package ru.anydevprojects.flashcards.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

class FilePathConverter(private val applicationContext: Context) {
    fun pathFileFromContentUri(uriString: String): Result<String> = kotlin.runCatching {
        val contentUri = Uri.parse(uriString)
        val fileNameWithExtension = contentUri.path?.substringAfterLast("/") ?: "fileName.apkg"

        val tempFile = File(applicationContext.cacheDir, fileNameWithExtension)
        tempFile.createNewFile()

        applicationContext.contentResolver.openInputStream(contentUri)?.use { inputStream ->
            FileOutputStream(tempFile).use { oStream ->
                inputStream.copyTo(oStream)
            }
        }

        tempFile.path
    }
}
