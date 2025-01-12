package ru.anydevprojects.flashcards.home.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.anydevprojects.flashcards.home.domain.models.ImportState
import ru.anydevprojects.flashcards.home.domain.models.ImportStep
import ru.anydevprojects.flashcards.utils.ApkgFileManager
import ru.anydevprojects.flashcards.utils.FilePathConverter

class ImportFileUseCase(
    private val filePathConverter: FilePathConverter,
    private val apkgFileManager: ApkgFileManager
) {
    operator fun invoke(uriString: String): Flow<ImportState> = flow {
        emit(ImportState.Starting)

        runCatching {
            emit(ImportState.Progress(step = ImportStep.INITIALIZING))
            val filePath = filePathConverter.pathFileFromContentUri(uriString)

            emit(ImportState.Progress(step = ImportStep.READING_FILE))

            filePath.onSuccess { path ->
                apkgFileManager.extractFile(path).onSuccess { unzippedFile ->
                    apkgFileManager.copyMediaFiles(unzippedFile)

                    val collectionDatabaseModel = apkgFileManager.getDataFromDatabaseFile(
                        unzippedFile
                    )
                    Log.d("dataFromDB", collectionDatabaseModel.getOrNull().toString())
                }
            }

            emit(ImportState.Progress(step = ImportStep.PROCESSING_DATA))

            emit(ImportState.Progress(step = ImportStep.FINALIZING))
            // TODO import file logic
        }.onSuccess {
            emit(ImportState.Success("success"))
        }.onFailure {
            emit(ImportState.Error(it))
        }
    }

    private fun moveResourceFiles(filePath: String) {
    }
}
