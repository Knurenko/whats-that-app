package com.knurenko.whatsthat.presentation.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * @author Knurenko Bogdan 30.08.2023
 */
class AppColors(
    val accent: Color,
    val error: Color,
    val background: Color,
    val shadow: Color,
    val text: Color
)

val lightColors = AppColors(
    accent = Color(0xFF673AB7),
    error = Color(0xFFF44336),
    background = Color(0xFFFFFFFF),
    shadow = Color(0xFF000000),
    text = Color(0xFF000000)
)

val darkColors = AppColors(
    accent = Color(0xFFFF9800),
    error = Color(0xFFF44336),
    background = Color(0xFF000000),
    shadow = Color(0xFFFFFFFF),
    text = Color(0xFFFFFFFF)
)

