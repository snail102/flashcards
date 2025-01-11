package ru.anydevprojects.flashcards.di

import org.koin.dsl.module
import ru.anydevprojects.flashcards.home.di.homeModule
import ru.anydevprojects.flashcards.utils.di.utilsModule

val appModule = module {
    utilsModule
    homeModule
}
