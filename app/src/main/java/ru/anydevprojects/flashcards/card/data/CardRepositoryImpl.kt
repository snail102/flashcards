package ru.anydevprojects.flashcards.card.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anydevprojects.flashcards.card.domain.CardRepository
import ru.anydevprojects.flashcards.utils.CollectionDatabaseModel
import ru.anydevprojects.flashcards.utils.toEntity

class CardRepositoryImpl(private val cardDao: CardDao) : CardRepository {

    override suspend fun importCards(collectionDatabaseModel: CollectionDatabaseModel) {
        withContext(Dispatchers.Default) {
            val nidWithCards = collectionDatabaseModel.cards.associateBy { it.nid }
            val cardEntityList = collectionDatabaseModel.notes.mapNotNull { note ->
                nidWithCards[note.id]?.let { card ->
                    val parts = note.flds.split("\u001f")

                    card.toEntity(
                        front = parts.getOrNull(0).orEmpty(),
                        back = parts.getOrNull(1).orEmpty()
                    )
                }
            }
            cardDao.insertCards(cardEntityList)
        }
    }
}
