package ru.anydevprojects.flashcards.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.anydevprojects.flashcards.bottomHost.navigation.BottomHostScreenDestination
import ru.anydevprojects.flashcards.bottomHost.presentation.BottomHostScreen
import ru.anydevprojects.flashcards.ui.theme.FlashcardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            FlashcardsTheme {
                AppHost(
                    navController = navController,
                    startDestination = BottomHostScreenDestination
                )
            }
        }
    }
}

@Composable
private fun AppHost(navController: NavHostController, startDestination: Any) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<BottomHostScreenDestination> {
            BottomHostScreen()
        }
    }
}
