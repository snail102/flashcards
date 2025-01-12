package ru.anydevprojects.flashcards.utils

data class NotesAnkiModel(
    val id: Long, // Уникальный идентификатор заметки
    val guid: String, // Глобальный уникальный идентификатор
    val mid: Long, // Идентификатор модели заметки
    val mod: Long, // Временная метка последнего изменения
    val usn: Int, // Номер синхронизации
    val tags: String, // Теги, связанные с заметкой
    val flds: String, // Поля заметки, разделенные символом \x1f
    val sfld: String, // Поле, используемое для сортировки
    val csum: Long, // Контрольная сумма содержимого
    val flags: Int, // Флаги, связанные с заметкой
    val data: String // Дополнительные данные
)
