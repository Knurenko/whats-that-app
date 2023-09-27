package com.knurenko.whatsthat.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.knurenko.whatsthat.presentation.navigation.LocalRouter
import com.knurenko.whatsthat.presentation.navigation.NavGraph
import com.knurenko.whatsthat.presentation.navigation.NavRouter
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme
import com.knurenko.whatsthat.presentation.ui.theme.ThemeSwitchViewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val router = remember { NavRouter(navController) }
    val themeViewModel: ThemeSwitchViewModel = koinInject()

    AppTheme(isDarkMode = themeViewModel.isDarkTheme) {
        CompositionLocalProvider(LocalRouter provides router) {
            NavGraph(navHostController = navController)
        }
    }
}