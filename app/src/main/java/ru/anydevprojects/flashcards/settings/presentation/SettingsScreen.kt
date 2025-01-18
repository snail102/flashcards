package ru.anydevprojects.flashcards.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.anydevprojects.flashcards.ui.theme.FlashcardsTheme

@Composable
fun SettingsScreen() {
    SettingsContent()
}

@Composable
private fun SettingsContent() {
}

@Preview
@Composable
private fun SettingsContentPreview() {
    FlashcardsTheme {
        SettingsContent()
    }
}
