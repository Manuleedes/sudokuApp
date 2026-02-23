package com.lidigu.sudoku.ui.activegame

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.lidigu.sudoku.R
import com.lidigu.sudoku.common.makeToast
import com.lidigu.sudoku.ui.GraphSudokuTheme
import com.lidigu.sudoku.ui.activegame.buildLogic.buildActiveGameLogic
import com.lidigu.sudoku.ui.newgame.NewGameActivity

class ActiveGameActivity : AppCompatActivity(), ActiveGameContainer {
    private lateinit var logic: ActiveGameLogic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ActiveGameViewModel()
        logic = buildActiveGameLogic(this, viewModel, applicationContext)

        setContent {
            GraphSudokuTheme{
                ActiveGameScreen(
                    onEventHandler = logic::onEvent,
                    viewModel
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logic.onEvent(ActiveGameEvent.OnStart)
    }

    override fun onStop() {
        super.onStop()
        logic.onEvent(ActiveGameEvent.OnStop)
    }

    override fun showError() = makeToast(getString(R.string.generic_error))

    override fun onNewGameClick() {
        startActivity(
            Intent(
                this,
                NewGameActivity::class.java
            )
        )
    }
}