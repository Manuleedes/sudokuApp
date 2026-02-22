package com.lidigu.sudoku.persistence


import com.lidigu.sudoku.domain.ISettingsStorage
import com.lidigu.sudoku.domain.IgameDataStorage
import com.lidigu.sudoku.domain.IgameRepository
import com.lidigu.sudoku.domain.Settings
import com.lidigu.sudoku.domain.gameStorageResult
import com.lidigu.sudoku.domain.settingsStorageResult
import com.lidigu.sudoku.domain.sudokuPuzzle

class GameRepositoryImpl(
    private val gameStorage: IgameDataStorage,
    private val settingsStorage: ISettingsStorage
): IgameRepository {
    override suspend fun saveGame(
        elapsedTime: Long,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when(val getCurrentGameResult = gameStorage.getCurrentGame()){
            is gameStorageResult.OnSuccess -> {
                gameStorage.updateGame(
                    getCurrentGameResult.currentGame.copy(
                        elapsedTime = elapsedTime
                    )
                )
                onSuccess(Unit)
            }
            is gameStorageResult.OnError -> {
                onError(getCurrentGameResult.exception)
            }
        }
    }

    override suspend fun updateGame(
        game: sudokuPuzzle,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when(val updateGameResult: gameStorageResult = gameStorage.updateGame(game)){
            is gameStorageResult.OnSuccess -> onSuccess(Unit)
            is gameStorageResult.OnError ->onError(updateGameResult.exception)
        }
    }

    override suspend fun createNewGame(
        settings: Settings,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when(val updateSettingsResult = settingsStorage.updateSettings(settings)){
          is  settingsStorageResult.OnSuccess -> {
                when (val updateGameResult = createAndWriteNewGame(settings)){
                    is gameStorageResult.OnError -> onError(updateGameResult.exception)
                    is gameStorageResult.OnSuccess -> onSuccess(Unit)
                }
            }
            is settingsStorageResult.OnError -> onError(updateSettingsResult.exception)
        }

    }
    private suspend fun createAndWriteNewGame(settings: Settings) : gameStorageResult{
        return gameStorage.updateGame(
            sudokuPuzzle(
                settings.boundary,
                settings.difficulty
            )
        )
    }

    override suspend fun updateNode(
        x: Int,
        y: Int,
        color: Int,
        elapsedTime: Long,
        onSuccess: (isComplete: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val result = gameStorage.updateNode(x,y, color, elapsedTime)){
            is gameStorageResult.OnSuccess -> onSuccess(
                puzzleIsCompleted(result.currentGame)
            )
            is gameStorageResult.OnError -> onError(
                result.exception
            )

        }
    }

    override suspend fun getCurrentGame(
        onSuccess: (currentGame: sudokuPuzzle, isComplete: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getCurrentGameResult = gameStorage.getCurrentGame()){
            is gameStorageResult.OnSuccess -> onSuccess(
                getCurrentGameResult.currentGame,
                puzzleIsCompleted(
                    getCurrentGameResult.currentGame
                )
            )
            is gameStorageResult.OnError -> {
                when(val getSettingsResult = settingsStorage.getSettings()){
                    is settingsStorageResult.OnSuccess ->{
                        when(val updateGameResult = createAndWriteNewGame(getSettingsResult.settings)){
                            is gameStorageResult.OnSuccess -> onSuccess(
                                updateGameResult.currentGame,
                                puzzleIsCompleted(
                                    updateGameResult.currentGame
                                )
                            )
                            is gameStorageResult.OnError -> onError(updateGameResult.exception)
                        }
                    }
                    is settingsStorageResult.OnError -> onError(getSettingsResult.exception)

                }
            }

        }
    }

    override suspend fun getSettings(
        onSuccess: (Settings) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getSettingsResult = settingsStorage.getSettings()){
            is settingsStorageResult.OnError -> onError(getSettingsResult.exception)
            is settingsStorageResult.OnSuccess -> onSuccess(getSettingsResult.settings)
        }
    }

    override suspend fun updateSettings(
        settings:Settings,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        settingsStorage.updateSettings(settings)
        onSuccess(Unit)
    }


}