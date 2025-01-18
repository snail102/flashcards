package ru.anydevprojects.flashcards.deck.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anydevprojects.flashcards.deck.domain.DeckRepository
import ru.anydevprojects.flashcards.utils.CollectionDatabaseModel
import ru.anydevprojects.flashcards.utils.toEntity

class DeckRepositoryImpl(private val deckDao: DeckDao) : DeckRepository {
    override suspend fun importDecks(collectionDatabaseModel: CollectionDatabaseModel) {
        withContext(Dispatchers.Default) {
            val nameToDeckMap = collectionDatabaseModel.decks.associateBy {
                it.name.split("::").lastOrNull() ?: it.name
            }

            val deckEntityList = collectionDatabaseModel.decks.map { deck ->
                val deckNameParts = deck.name.split("::")
                val deckParentName = deckNameParts.getOrNull(deckNameParts.size - 2)

                deck.toEntity(
                    parentId = deckParentName?.let {
                        nameToDeckMap[it]?.id
                    }
                )
            }

            deckDao.insertDecks(decks = deckEntityList)
        }
    }
}
