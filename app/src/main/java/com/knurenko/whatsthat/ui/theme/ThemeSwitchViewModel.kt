package com.knurenko.whatsthat.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @author Knurenko Bogdan 30.08.2023
 */
class ThemeSwitchViewModel : ViewModel() {

    var isDarkTheme by mutableStateOf(false)
        private set

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
    }
}