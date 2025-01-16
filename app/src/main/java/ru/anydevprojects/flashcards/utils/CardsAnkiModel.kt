package ru.anydevprojects.flashcards.utils

import ru.anydevprojects.flashcards.card.data.models.CardEntity

data class CardsAnkiModel(
    val id: Long, // Уникальный идентификатор карточки
    val nid: Long, // Идентификатор связанной заметки
    val did: Long, // Идентификатор колоды
    val ord: Int, // Порядковый номер шаблона в заметке
    val mod: Long, // Временная метка последнего изменения
    val usn: Int, // Номер синхронизации
    val type: Int, // Тип карточки (0 = новая, 1 = изучаемая, 2 = изученная)
    val queue: Int, // Состояние очереди
    val due: Long, // Дата следующего показа
    val ivl: Int, // Интервал в днях до следующего показа
    val factor: Int, // Множитель интервала (в процентах)
    val reps: Int, // Общее количество повторений
    val lapses: Int, // Количество срывов
    val left: Int, // Оставшееся количество повторений в текущей стадии
    val odue: Long, // Оригинальная дата следующего показа для отложенных карточек
    val odid: Long // Оригинальный идентификатор колоды для отложенных карточек
)

fun CardsAnkiModel.toEntity(front: String, back: String): CardEntity = CardEntity(
    id = id,
    did = did,
    mod = mod,
    front = front,
    back = back
)
