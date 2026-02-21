package com.lidigu.sudoku.persistence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

internal val Context.settingsDataStore: DataStore<GameSettings> by dataStore(
    fileName = "game_settings.pb",
    serializer = GameSettingsSerializer
)

internal val Context.statisticsDataStore: DataStore<Statistics> by dataStore(
    fileName = "user_statistics.pb",
    serializer = StatisticsSerializer
)