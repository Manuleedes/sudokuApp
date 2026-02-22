package com.lidigu.sudoku.persistence

import android.R
import com.lidigu.sudoku.domain.IgameDataStorage
import com.lidigu.sudoku.domain.gameStorageResult
import com.lidigu.sudoku.domain.getHash
import com.lidigu.sudoku.domain.sudokuPuzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


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
            val fileOutputStream = FileOutputStream(pathToStorageFile)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(game)
            objectOutputStream.close()
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun updateNode(x: Int, y: Int, color: Int,elapsedTime: Long):
            gameStorageResult = withContext(Dispatchers.IO) {
       try {
            val game = getGame()
           game.graph[getHash(x,y)]!!.first.color = color
           game.elapsedTime = elapsedTime
           updateGameData(game)
           gameStorageResult.OnSuccess(game)
       }catch (e: Exception){
           gameStorageResult.OnError(e)
       }
    }
    private fun getGame(): sudokuPuzzle{
        try {
            var game: sudokuPuzzle
            val fileInputStream = FileInputStream(pathToStorageFile)
            val objectInputStream = ObjectInputStream(fileInputStream)
            game = objectInputStream.readObject() as sudokuPuzzle
            objectInputStream.close()
            return game
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getCurrentGame(): gameStorageResult =
        withContext(Dispatchers.IO)
        {
            try {
                gameStorageResult.OnSuccess(getGame())
            }catch (e: Exception){
                gameStorageResult.OnError(e)
            }
            }

    }
