package com.lidigu.sudoku.ui
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = primaryGreen,
    secondary = textColorLight,
    surface = lightGrey,
    primaryContainer = gridLineColorLight,
    onPrimary = accentAmber,
    onSurface = accentAmber
)

private val DarkColorScheme = darkColorScheme(
    // main background color
    primary = primaryCharcoal,
    // used for text color
    secondary = textColorDark,
    // background of sudoku board
    surface = lightGreyAlpha,
    // grid lines of sudoku board
    primaryContainer = gridLineColorLight,
    onPrimary = accentAmber,
    onSurface = accentAmber
)

@Composable
fun GraphSudokuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

