package com.lidigu.sudoku.domain

import android.provider.Settings

interface IgameRepository {
    suspend fun saveGame(
        elapsedTime: Long,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateGame(
        game: sudokuPuzzle,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )
    suspend fun updateNode(
        x: Int,
        y: Int,
        color: Int,
        elapsedTime: Long,
        onSuccess: (isComplete: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
    suspend fun getCurrentGame(
        onSuccess: (currentGame: sudokuPuzzle, isComplete: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
    suspend fun getSettings(
        onSuccess: (Settings) -> Unit,
        onError: (Exception) -> Unit

    )
    suspend fun updateSettings(
        settings: settings,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )
}