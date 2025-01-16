package ru.anydevprojects.flashcards.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.anydevprojects.flashcards.card.data.CardDao
import ru.anydevprojects.flashcards.card.data.models.CardEntity
import ru.anydevprojects.flashcards.deck.data.DeckDao
import ru.anydevprojects.flashcards.deck.data.models.DeckEntity

@Database(
    entities = [CardEntity::class, DeckEntity::class],
    version = 1
)
abstract class FlashcardsDatabase : RoomDatabase() {
    abstract val deckDao: DeckDao
    abstract val cardDao: CardDao
}
