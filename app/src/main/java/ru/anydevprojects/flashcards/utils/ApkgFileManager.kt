package ru.anydevprojects.flashcards.utils

import java.io.File
import java.util.zip.ZipFile

class ApkgFileManager {
    fun extractFile(filePath: String, outputDir: String) {
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
    }
}
