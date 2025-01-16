package ru.anydevprojects.flashcards.deck.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

private const val PARENT_ID_COLUMN = "parent_id"

@Entity(
    tableName = "decks",
    indices = [Index(value = [PARENT_ID_COLUMN])]
)
data class DeckEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    @ColumnInfo(name = PARENT_ID_COLUMN)
    val parentId: Long?
)
