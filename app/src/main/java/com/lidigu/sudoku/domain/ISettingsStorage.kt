package com.lidigu.sudoku.domain

interface ISettingsStorage {
    suspend fun getSettings(): settingsStorageResult
    suspend fun updateSettings(settings: settings): settingsStorageResult
}


sealed class settingsStorageResult{
    data class OnSuccess(val settings: settings): settingsStorageResult()
    data class OnError(val exception: Exception): settingsStorageResult()
}