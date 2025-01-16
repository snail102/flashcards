package ru.anydevprojects.flashcards.deck.data.models

data class DeckWithNested(val id: Long, val name: String, val childs: List<DeckWithNested>)
