package com.lidigu.sudoku.domain

interface ISettingsStorage {
    suspend fun getSettings(): settingsStorageResult
    suspend fun updateSettings(settings: Settings): settingsStorageResult
}


sealed class settingsStorageResult{
    data class OnSuccess(val settings: Settings): settingsStorageResult()
    data class OnError(val exception: Exception): settingsStorageResult()
}