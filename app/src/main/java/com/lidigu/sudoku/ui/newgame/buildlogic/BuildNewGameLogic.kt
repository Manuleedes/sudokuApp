package com.lidigu.sudoku.ui.newgame.buildlogic

import android.content.Context
import com.lidigu.sudoku.common.ProductionDispatcherProvider
import com.lidigu.sudoku.persistence.*
import com.lidigu.sudoku.ui.newgame.NewGameContainer
import com.lidigu.sudoku.ui.newgame.NewGameLogic
import com.lidigu.sudoku.ui.newgame.NewGameViewModel

internal fun buildNewGameLogic(
    container: NewGameContainer,
    viewModel: NewGameViewModel,
    context: Context
): NewGameLogic {
    return NewGameLogic(
        container,
        viewModel,
        GameRepositoryImpl(
            LocalGameStorageImpl(context.filesDir.path),
            LocalSettingsStorageImpl(context.settingsDataStore)
        ),
        LocalStatisticsStorageImpl(
            context.statsDataStore
        ),
        ProductionDispatcherProvider
    )
}