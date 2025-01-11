package ru.anydevprojects.flashcards.home.presentation

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

private const val FILE_CHOOSER_FILTER_TYPE = "application/octet-stream"

@Composable
fun HomeScreen() {
    val fileChooserLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Log.d("activityResult", "selected file: $uri")
        }
    HomeScreenContent(onImportBtnClick = {
        fileChooserLauncher.launch(FILE_CHOOSER_FILTER_TYPE)
    })
}

@Composable
private fun HomeScreenContent(onImportBtnClick: () -> Unit) {
    Scaffold { paddingValue ->
        Column(
            modifier = Modifier.padding(paddingValue)
        ) {
            Button(
                onClick = onImportBtnClick
            ) {
                Text(
                    "Import deck"
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        onImportBtnClick = {}
    )
}
