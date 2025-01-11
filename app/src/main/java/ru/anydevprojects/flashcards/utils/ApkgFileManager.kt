package ru.anydevprojects.flashcards.utils

import android.content.Context
import java.io.File
import java.util.zip.ZipFile

class ApkgFileManager(private val applicationContext: Context) {
    fun extractFile(filePath: String): String {
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
        return outputDir
    }
}
