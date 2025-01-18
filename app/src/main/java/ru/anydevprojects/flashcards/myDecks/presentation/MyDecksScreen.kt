package ru.anydevprojects.flashcards.myDecks.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.anydevprojects.flashcards.ui.theme.FlashcardsTheme

@Composable
fun MyDecksScreen() {
    MyDecksContent()
}

@Composable
private fun MyDecksContent() {
}

@Preview
@Composable
private fun MyDecksContentPreview() {
    FlashcardsTheme {
        MyDecksContent()
    }
}
