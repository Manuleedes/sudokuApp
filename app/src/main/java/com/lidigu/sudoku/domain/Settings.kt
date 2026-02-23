package com.lidigu.sudoku.domain

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val difficulty: Difficulty,
    val boundary: Int
)
