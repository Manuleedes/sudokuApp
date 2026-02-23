package com.lidigu.sudoku.ui.activegame.buildLogic

import android.content.Context
import com.lidigu.sudoku.common.ProductionDispatcherProvider
import com.lidigu.sudoku.persistence.GameRepositoryImpl
import com.lidigu.sudoku.persistence.LocalGameStorageImpl
import com.lidigu.sudoku.persistence.settingsDataStore
import com.lidigu.sudoku.persistence.statisticsDataStore
import com.lidigu.sudoku.persistence.LocalSettingsStorageImpl
import com.lidigu.sudoku.persistence.LocalStatisticsStorageImpl
import com.lidigu.sudoku.ui.activegame.ActiveGameContainer
import com.lidigu.sudoku.ui.activegame.ActiveGameLogic
import com.lidigu.sudoku.ui.activegame.ActiveGameViewModel


internal fun buildActiveGameLogic(
    container: ActiveGameContainer,
    viewModel: ActiveGameViewModel,
    context: Context
): ActiveGameLogic {
    return ActiveGameLogic(
        container,
        viewModel,
        GameRepositoryImpl(
            LocalGameStorageImpl(context.filesDir.path),
            LocalSettingsStorageImpl(context.settingsDataStore)

        ),
        LocalStatisticsStorageImpl(
            context.statisticsDataStore
        ),
        ProductionDispatcherProvider
    )
}