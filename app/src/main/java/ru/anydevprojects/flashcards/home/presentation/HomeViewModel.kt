package ru.anydevprojects.flashcards.home.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.flashcards.core.ui.BaseViewModel
import ru.anydevprojects.flashcards.home.presentation.models.HomeEvent
import ru.anydevprojects.flashcards.home.presentation.models.HomeIntent
import ru.anydevprojects.flashcards.home.presentation.models.HomeState

class HomeViewModel :
    BaseViewModel<HomeState, HomeState.Content, HomeIntent, HomeEvent>(
        initialStateAndDefaultContentState = {
            HomeState.Content() to HomeState.Content()
        }
    ) {
    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnImportBtnClick -> openFileChooser()
            is HomeIntent.OnSelectedFile -> importFile(intent.uri)
        }
    }

    private fun importFile(uri: Uri?) {
        if (uri == null) {
            return
        }
    }

    private fun openFileChooser() {
        viewModelScope.launch {
            emitEvent(HomeEvent.OpenFileChooser)
        }
    }
}
