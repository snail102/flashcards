package ru.anydevprojects.flashcards.deck.data

import ru.anydevprojects.flashcards.deck.domain.DeckRepository
import ru.anydevprojects.flashcards.utils.CollectionDatabaseModel

class DeckRepositoryImpl(private val deckDao: DeckDao) : DeckRepository {
    override suspend fun importDecks(collectionDatabaseModel: CollectionDatabaseModel) {
        TODO("Not yet implemented")
    }
}
