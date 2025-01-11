package ru.anydevprojects.flashcards.home.presentation.models

import ru.anydevprojects.flashcards.core.ui.ContentViewState
import ru.anydevprojects.flashcards.core.ui.ViewState

sealed interface HomeState : ViewState {

    data class Content(val selectedFilePath: String = "") :
        HomeState,
        ContentViewState
}
