package ru.anydevprojects.flashcards.home.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.flashcards.home.domain.ImportFileUseCase
import ru.anydevprojects.flashcards.home.presentation.HomeViewModel

val homeModule = module {
    factory { ImportFileUseCase(get(), get()) }
    viewModel { HomeViewModel(get()) }
}
