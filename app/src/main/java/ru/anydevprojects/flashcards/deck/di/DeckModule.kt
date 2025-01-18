package ru.anydevprojects.flashcards.deck.di

import org.koin.dsl.module
import ru.anydevprojects.flashcards.core.database.FlashcardsDatabase
import ru.anydevprojects.flashcards.deck.data.DeckDao
import ru.anydevprojects.flashcards.deck.data.DeckRepositoryImpl
import ru.anydevprojects.flashcards.deck.domain.DeckRepository

val deckModule = module {
    factory<DeckRepository> { DeckRepositoryImpl(get()) }
    single<DeckDao> { provideDao(get()) }
}

private fun provideDao(flashcardsDatabase: FlashcardsDatabase): DeckDao = flashcardsDatabase.deckDao
