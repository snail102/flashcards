package ru.anydevprojects.flashcards.deck.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anydevprojects.flashcards.deck.data.models.DeckEntity
import ru.anydevprojects.flashcards.deck.data.models.DeckWithNested

@Dao
interface DeckDao {

    @Insert
    suspend fun insert(deck: DeckEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDecks(decks: List<DeckEntity>)

    @Query("SELECT * FROM decks WHERE parent_id = :parentId")
    suspend fun getDecksByParentId(parentId: Long): List<DeckEntity>

    @Query("SELECT * FROM decks WHERE id = :id")
    suspend fun getDeckById(id: Long): DeckEntity?

    @Query("SELECT * FROM decks")
    suspend fun getAllDecks(): List<DeckEntity>
}

suspend fun DeckDao.getAllDecksWithNested(): List<DeckWithNested> {
    val allDecks = getAllDecks()

    val deckMap = allDecks.groupBy { it.parentId }

    fun buildNestedDecks(parentId: Long?): List<DeckWithNested> = deckMap[parentId]?.map { deck ->
        DeckWithNested(
            id = deck.id,
            name = deck.name,
            childs = buildNestedDecks(deck.id)
        )
    } ?: emptyList()

    return buildNestedDecks(null)
}
