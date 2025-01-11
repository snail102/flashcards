package ru.anydevprojects.flashcards.utils.di

import org.koin.dsl.module
import ru.anydevprojects.flashcards.utils.ApkgFileManager
import ru.anydevprojects.flashcards.utils.FilePathConverter

val utilsModule = module {
    factory { FilePathConverter(get()) }
    factory { ApkgFileManager(get()) }
}
