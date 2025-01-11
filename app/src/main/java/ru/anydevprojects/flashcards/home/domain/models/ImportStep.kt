package ru.anydevprojects.flashcards.home.domain.models

enum class ImportStep {
    INITIALIZING,
    READING_FILE,
    PROCESSING_DATA,
    FINALIZING
}
