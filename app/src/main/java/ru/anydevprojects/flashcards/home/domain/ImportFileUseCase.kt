package ru.anydevprojects.flashcards.home.domain

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
    operator fun invoke(filePath: String): Flow<ImportState> = flow {
        emit(ImportState.Starting)

        runCatching {
            emit(ImportState.Progress(step = ImportStep.INITIALIZING))

            apkgFileManager.extractFile(filePath)

            emit(ImportState.Progress(step = ImportStep.READING_FILE))

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
