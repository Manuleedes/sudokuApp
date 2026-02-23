package com.lidigu.sudoku.domain

import kotlinx.serialization.Serializable

@Serializable
enum class Difficulty(val modifier: Double) {
    EASY(0.50),
    MEDIUM(0.40),
    HARD(0.35)
}