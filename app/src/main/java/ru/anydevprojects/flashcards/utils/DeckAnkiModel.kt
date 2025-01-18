package ru.anydevprojects.flashcards.utils

import kotlinx.serialization.Serializable
import ru.anydevprojects.flashcards.deck.data.models.DeckEntity

@Serializable
data class DeckAnkiModel(val id: Long, val name: String)

fun DeckAnkiModel.toEntity(parentId: Long?): DeckEntity = DeckEntity(
    id = id,
    name = name,
    parentId = parentId
)
