package com.knurenko.whatsthat.presentation.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.knurenko.whatsthat.R


/**
 * App fonts & styles
 * @author Knurenko Bogdan 30.08.2023
 */
data class AppTypography(
    val defaultFontFamily: FontFamily = poppinsFontFamily
)

private val poppinsFontFamily = FontFamily(
    Font(R.font.poppins, FontWeight.Normal, FontStyle.Normal)
)