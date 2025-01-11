package ru.anydevprojects.flashcards.home.presentation.models

import android.net.Uri
import ru.anydevprojects.flashcards.core.ui.ViewIntent

sealed interface HomeIntent : ViewIntent {

    data object OnImportBtnClick : HomeIntent
    data class OnSelectedFile(val uri: Uri?) : HomeIntent
}
