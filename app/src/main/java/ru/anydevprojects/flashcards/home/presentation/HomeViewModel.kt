package ru.anydevprojects.flashcards.home.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.flashcards.core.ui.BaseViewModel
import ru.anydevprojects.flashcards.home.domain.ImportFileUseCase
import ru.anydevprojects.flashcards.home.domain.models.ImportState
import ru.anydevprojects.flashcards.home.presentation.models.HomeEvent
import ru.anydevprojects.flashcards.home.presentation.models.HomeIntent
import ru.anydevprojects.flashcards.home.presentation.models.HomeState

class HomeViewModel(private val importFileUseCase: ImportFileUseCase) :
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
        val uriString = uri?.toString() ?: return

        viewModelScope.launch {
            importFileUseCase(uriString = uriString).collect { importState: ImportState ->
                Log.d("ImportFileUseCase", "state: $importState")
            }
        }
    }

    private fun openFileChooser() {
        viewModelScope.launch {
            emitEvent(HomeEvent.OpenFileChooser)
        }
    }
}
