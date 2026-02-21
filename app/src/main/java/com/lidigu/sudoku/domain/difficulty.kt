package com.lidigu.sudoku.domain

enum class difficulty(val modifier: Double) {
    EASY(0.50),
    MEDIUM(0.40),
    HARD(0.35)
}