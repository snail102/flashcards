package ru.anydevprojects.flashcards.card.domain

import ru.anydevprojects.flashcards.utils.CollectionDatabaseModel

interface CardRepository {

    suspend fun importCards(collectionDatabaseModel: CollectionDatabaseModel)
}
