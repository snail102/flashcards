package ru.anydevprojects.flashcards.card.di

import org.koin.dsl.module
import ru.anydevprojects.flashcards.card.data.CardDao
import ru.anydevprojects.flashcards.card.data.CardRepositoryImpl
import ru.anydevprojects.flashcards.card.domain.CardRepository
import ru.anydevprojects.flashcards.core.database.FlashcardsDatabase

val cardModule = module {
    factory<CardRepository> { CardRepositoryImpl(get()) }
    single<CardDao> { provideDao(get()) }
}

private fun provideDao(flashcardsDatabase: FlashcardsDatabase): CardDao = flashcardsDatabase.cardDao
