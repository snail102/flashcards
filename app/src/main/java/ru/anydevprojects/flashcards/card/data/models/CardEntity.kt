package ru.anydevprojects.flashcards.card.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey
    val id: Long, // Уникальный идентификатор карточки
    val did: Long, // Идентификатор колоды
    val mod: Long, // Временная метка последнего изменения
    val front: String, // Вопрос
    val back: String // Ответ
)
