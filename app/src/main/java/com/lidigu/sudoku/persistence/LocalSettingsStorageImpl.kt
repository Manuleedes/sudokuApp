package com.lidigu.sudoku.persistence

import androidx.datastore.core.DataStore
import com.lidigu.sudoku.domain.Difficulty
import com.lidigu.sudoku.domain.ISettingsStorage
import com.lidigu.sudoku.domain.Settings
import com.lidigu.sudoku.domain.SettingsStorageResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalSettingsStorageImpl(
    private val dataStore: DataStore<GameSettings>
) : ISettingsStorage {

    override suspend fun getSettings(): SettingsStorageResult {
        return try {
            val settings = dataStore.data.map { protoSettings ->
                Settings(
                    mapProtoDifficulty(protoSettings.difficulty),
                    protoSettings.boundary
                )
            }.first()
            SettingsStorageResult.OnSuccess(settings)
        } catch (e: Exception) {
            SettingsStorageResult.OnError(e)
        }
    }

    override suspend fun updateSettings(settings: Settings): SettingsStorageResult {
        return try {
            dataStore.updateData { currentProtoSettings ->
                currentProtoSettings.toBuilder()
                    .setBoundary(settings.boundary)
                    .setDifficulty(mapToProtoDifficulty(settings.difficulty))
                    .build()
            }
            SettingsStorageResult.OnSuccess(settings)
        } catch (e: Exception) {
            SettingsStorageResult.OnError(e)
        }
    }

    private fun mapProtoDifficulty(protoDifficulty: GameSettings.ProtoDifficulty): Difficulty {
        return when (protoDifficulty) {
            GameSettings.ProtoDifficulty.EASY -> Difficulty.EASY
            GameSettings.ProtoDifficulty.MEDIUM -> Difficulty.MEDIUM
            GameSettings.ProtoDifficulty.HARD -> Difficulty.HARD
            else -> Difficulty.MEDIUM
        }
    }

    private fun mapToProtoDifficulty(difficulty: Difficulty): GameSettings.ProtoDifficulty {
        return when (difficulty) {
            Difficulty.EASY -> GameSettings.ProtoDifficulty.EASY
            Difficulty.MEDIUM -> GameSettings.ProtoDifficulty.MEDIUM
            Difficulty.HARD -> GameSettings.ProtoDifficulty.HARD
        }
    }
}
