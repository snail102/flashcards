package ru.anydevprojects.flashcards.card.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.anydevprojects.flashcards.card.data.models.CardEntity

@Dao
interface CardDao {

    @Insert
    suspend fun insertCard(cardEntity: CardEntity)

    @Insert
    suspend fun insertCards(cards: List<CardEntity>)

    @Query("SELECT * FROM cards WHERE did = :deckId")
    suspend fun getAllCardsByDeckId(deckId: Long): List<CardEntity>

    @Query("SELECT * FROM cards")
    suspend fun getAllCards(): List<CardEntity>
}
