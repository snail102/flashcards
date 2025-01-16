package ru.anydevprojects.flashcards.di

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module
import ru.anydevprojects.flashcards.card.di.cardModule
import ru.anydevprojects.flashcards.core.database.FlashcardsDatabase
import ru.anydevprojects.flashcards.deck.di.deckModule
import ru.anydevprojects.flashcards.home.di.homeModule
import ru.anydevprojects.flashcards.utils.di.utilsModule

val appModule = module {
    includes(
        utilsModule,
        homeModule,
        cardModule,
        deckModule
    )
    single { provideDataBase(get()) }
}

private fun provideDataBase(application: Application): FlashcardsDatabase = Room.databaseBuilder(
    application,
    FlashcardsDatabase::class.java,
    "flashcards"
).fallbackToDestructiveMigration().build()
