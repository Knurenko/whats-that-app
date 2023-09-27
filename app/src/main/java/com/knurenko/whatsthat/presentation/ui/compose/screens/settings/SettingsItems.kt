package com.knurenko.whatsthat.presentation.ui.compose.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.knurenko.whatsthat.R

/**
 * @author Knurenko Bogdan 22.09.2023
 */

@Composable
fun createThemeSettingsItem(isDarkModeSelected: Boolean, onClick: () -> Unit) = SettingsItem(
    title = stringResource(R.string.settings_ui_theme_title),
    currentValue = if (isDarkModeSelected) stringResource(R.string.ui_mode_dark) else stringResource(
        R.string.ui_mode_light
    ),
    onClick = onClick
)