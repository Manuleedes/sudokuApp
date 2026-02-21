package com.lidigu.sudoku.persistence

import com.lidigu.sudoku.domain.IgameDataStorage
import com.lidigu.sudoku.domain.gameStorageResult
import com.lidigu.sudoku.domain.sudokuPuzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File


private const val FILE_NAME = "game_state.txt"

class LocalGameStorageImpl(
    fileStorageDirectory: String,
    private val pathToStorageFile: File = File(fileStorageDirectory, FILE_NAME)
) : IgameDataStorage{
    override suspend fun updateGame(game: sudokuPuzzle): gameStorageResult
     = withContext(Dispatchers.IO) {
        try {
            updateGameData (game)
            gameStorageResult.OnSuccess(game)
        }catch (e: Exception){
            gameStorageResult.OnError(e)
        }
    }
    private fun updateGameData(game: sudokuPuzzle){
        try {

        }catch (e: Exception){
            throw 
        }
    }

    override suspend fun updateNode(
        x: Int,
        y: Int,
        elapsedTime: Long
    ): gameStorageResult {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentGame(): gameStorageResult {
        TODO("Not yet implemented")
    }


}