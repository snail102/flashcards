package ru.anydevprojects.flashcards.home.domain.models

sealed interface ImportState {
    data object Idle : ImportState
    data object Starting : ImportState
    data class Progress(val step: ImportStep) : ImportState
    data class Success(val result: String) : ImportState
    data class Error(val exception: Throwable) : ImportState
}
