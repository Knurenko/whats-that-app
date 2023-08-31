package com.knurenko.whatsthat.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Object with references to app_theme's colors & typography
 * @author Knurenko Bogdan 30.08.2023
 */
object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

private val LocalColors = staticCompositionLocalOf { lightColors }
private val LocalTypography = staticCompositionLocalOf { AppTypography() }

@Composable
fun AppTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    typography: AppTypography = AppTheme.typography,
    content: @Composable () -> Unit
) {
    val themedColors: AppColors = if (isDarkMode) darkColors else lightColors

    CompositionLocalProvider(
        LocalColors provides themedColors,
        LocalTypography provides typography
    ) {
        MaterialTheme {
            content()
        }
    }
}