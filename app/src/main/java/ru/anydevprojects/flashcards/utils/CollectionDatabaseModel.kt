package ru.anydevprojects.flashcards.utils

data class CollectionDatabaseModel(
    val cards: List<CardsAnkiModel>,
    val notes: List<NotesAnkiModel>,
    val decks: List<DeckAnkiModel>
)
