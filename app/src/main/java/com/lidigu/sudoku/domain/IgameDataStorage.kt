package com.lidigu.sudoku.domain

interface IgameDataStorage {
    suspend fun updateGame(game: sudokuPuzzle): gameStorageResult
    suspend fun updateNode(x: Int, y: Int, elapsedTime:Long): gameStorageResult
    suspend fun getCurrentGame(): gameStorageResult
}

sealed class gameStorageResult{
    data class OnSuccess(val currentGame: sudokuPuzzle): gameStorageResult()
    data class OnError(val exception: Exception): gameStorageResult()
}