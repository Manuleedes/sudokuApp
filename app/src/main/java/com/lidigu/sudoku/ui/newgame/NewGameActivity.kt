package com.lidigu.sudoku.ui.newgame

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.lidigu.sudoku.R
import com.lidigu.sudoku.common.makeToast
import com.lidigu.sudoku.ui.GraphSudokuTheme
import com.lidigu.sudoku.ui.activegame.ActiveGameActivity
import com.lidigu.sudoku.ui.newgame.buildlogic.buildNewGameLogic

/**
 * This feature is so simple that it is not even worth it to have a separate logic class
 */
class NewGameActivity : AppCompatActivity(), NewGameContainer {
    private lateinit var logic: NewGameLogic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = NewGameViewModel()

        setContent {
            GraphSudokuTheme {
                NewGameScreen(
                    onEventHandler = logic::onEvent,
                    viewModel
                )
            }
        }

        logic = buildNewGameLogic(this, viewModel, applicationContext)

    }

    override fun onStart() {
        super.onStart()
        logic.onEvent(NewGameEvent.OnStart)
    }

    override fun showError() = makeToast(getString(R.string.generic_error))

    override fun onDoneClick() {
        startActiveGameActivity()
    }

    /**
     * I want the other feature to be completely restarted each time
     */
    override fun onBackPressed() {
        startActiveGameActivity()
    }

    private fun startActiveGameActivity() {
        startActivity(
            Intent(
                this,
                ActiveGameActivity::class.java
            ).apply {
                this.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            }
        )
    }

}