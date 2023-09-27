package com.knurenko.whatsthat.presentation.ui.compose.screens.settings

import androidx.compose.runtime.Composable
import com.knurenko.whatsthat.presentation.navigation.LocalRouter
import com.knurenko.whatsthat.presentation.ui.theme.ThemeSwitchViewModel
import org.koin.compose.koinInject

/**
 * @author Knurenko Bogdan 22.09.2023
 */

@Composable
fun SettingsScreenController() {
    val navRouter = LocalRouter.current
    val themeViewModel: ThemeSwitchViewModel = koinInject()

    val settingsItems = listOf(
        createThemeSettingsItem(isDarkModeSelected = themeViewModel.isDarkTheme, onClick = { themeViewModel.toggleTheme() })
    )

    SettingsScreenView(
        onBackPress = { navRouter.goBack() },
        settingsItems
    )
}
