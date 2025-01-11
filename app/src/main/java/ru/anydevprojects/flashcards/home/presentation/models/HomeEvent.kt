package ru.anydevprojects.flashcards.home.presentation.models

import ru.anydevprojects.flashcards.core.ui.ViewEvent

sealed interface HomeEvent : ViewEvent {
    data object OpenFileChooser : HomeEvent
}
