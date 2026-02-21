package com.lidigu.sudoku.domain

import kotlinx.serialization.Serializable

data class sudokuNode(
    val x: Int,
    val y: Int,
    var color: Int = 0,
    var readOnly: Boolean = true
): Serializable{
    override fun hashCode(): Int {
        return getHash(x, y)
    }
}
internal fun getHash(x: Int, y: Int): Int{
    val newX = x*100
    return "$newX$y".toInt()
}
