package ru.anydevprojects.flashcards.bottomHost.presentation.models

import ru.anydevprojects.flashcards.R
import ru.anydevprojects.flashcards.myDecks.navigation.MyDecksScreenDestination
import ru.anydevprojects.flashcards.settings.navigation.SettingsScreenDestination

interface BottomBarNavItem {
    val nameStringResId: Int
    val route: Any
}

data class MyDecksNavItem(
    override val nameStringResId: Int = R.string.my_decks,
    override val route: Any = MyDecksScreenDestination
) : BottomBarNavItem

data class SettingsNavItem(
    override val nameStringResId: Int = R.string.settings,
    override val route: Any = SettingsScreenDestination
) : BottomBarNavItem
