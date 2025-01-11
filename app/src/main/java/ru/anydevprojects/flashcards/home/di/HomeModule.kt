package ru.anydevprojects.flashcards.home.di

import org.koin.dsl.module
import ru.anydevprojects.flashcards.home.domain.ImportFileUseCase

val homeModule = module {
    factory { ImportFileUseCase(get(), get()) }
}
