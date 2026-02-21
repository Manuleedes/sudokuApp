package com.lidigu.sudoku.domain

interface IStatisticsRepository {
    suspend fun getStatistics(
        onSuccess: (userStatistics) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateStatistics(
        time: Long,
        diff: Difficulty,
        boundary: Int,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
}