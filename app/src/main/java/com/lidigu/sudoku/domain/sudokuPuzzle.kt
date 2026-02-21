package com.lidigu.sudoku.domain

import kotlinx.serialization.Serializable
import java.util.LinkedList

data class sudokuPuzzle(
    val boundary: Int,
    val difficulty: Difficulty,
    val graph: LinkedHashMap<Int, LinkedList<sudokuNode>>
        = buildNewSudoku(boundary, difficulty).graph,
    var elapsedTime: Long = 0L
): Serializable{
    fun getValue(): LinkedHashMap<Int, LinkedList<sudokuNode>> = graph
}
