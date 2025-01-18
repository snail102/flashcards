package ru.anydevprojects.flashcards.bottomHost.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.anydevprojects.flashcards.bottomHost.presentation.models.MyDecksNavItem
import ru.anydevprojects.flashcards.bottomHost.presentation.models.SettingsNavItem
import ru.anydevprojects.flashcards.myDecks.navigation.MyDecksScreenDestination
import ru.anydevprojects.flashcards.myDecks.presentation.MyDecksScreen
import ru.anydevprojects.flashcards.settings.navigation.SettingsScreenDestination
import ru.anydevprojects.flashcards.ui.theme.FlashcardsTheme

@Composable
fun BottomHostScreen() {
    BottomHostContent()
}

@Composable
private fun BottomHostContent() {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                navHostController = navHostController
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navHostController,
            startDestination = MyDecksScreenDestination
        ) {
            composable<MyDecksScreenDestination> {
                MyDecksScreen()
            }

            composable<SettingsScreenDestination> {
                MyDecksScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val items = listOf(
        MyDecksNavItem(),
        SettingsNavItem()
    )

    NavigationBar(
        modifier = modifier
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = navHostController.currentDestination == screen.route,
                onClick = {
                    if (navHostController.currentDestination != screen.route) {
                        navHostController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                label = {
                    Text(
                        text = stringResource(screen.nameStringResId),
                        color = Color.White
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.LightGray
                ),
                icon = {}
            )
        }
    }
}

@Preview
@Composable
private fun BottomHostContentPreview() {
    FlashcardsTheme {
        BottomHostContent()
    }
}
