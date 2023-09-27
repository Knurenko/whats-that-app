package com.knurenko.whatsthat.presentation.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @author Knurenko Bogdan 30.08.2023
 */
class ThemeSwitchViewModel(
    private val nightModeStorage: NightModeStorage
) : ViewModel() {

    var isDarkTheme by mutableStateOf(nightModeStorage.isNightModeSelected)
        private set

    fun toggleTheme() {
        val flipped = !isDarkTheme
        isDarkTheme = flipped
        nightModeStorage.isNightModeSelected = flipped
    }
}