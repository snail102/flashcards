package ru.anydevprojects.flashcards.utils

import kotlinx.serialization.Serializable

@Serializable
data class Decks(val decks: Map<String, DeckAnkiModel>)
