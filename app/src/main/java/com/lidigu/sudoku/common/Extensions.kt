package com.lidigu.sudoku.common

import android.app.Activity
import android.widget.Toast
import com.lidigu.sudoku.R
import com.lidigu.sudoku.domain.Difficulty

internal fun Activity.makeToast(message: String){
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}
fun Long.toTime(): String =
    if (this >= 3600) "+59:59"
    else "%02d:%02d".format((this % 3600) / 60, this % 60)

val Difficulty.toLocalizedResource: Int
    get() {
        return when(this) {
            Difficulty.EASY -> R.string.easy
            Difficulty.MEDIUM -> R.string.medium
            Difficulty.HARD -> R.string.hard
        }
    }


