package ru.anydevprojects.flashcards.deck.domain

import ru.anydevprojects.flashcards.utils.CollectionDatabaseModel

interface DeckRepository {
    suspend fun importDecks(collectionDatabaseModel: CollectionDatabaseModel)
}
