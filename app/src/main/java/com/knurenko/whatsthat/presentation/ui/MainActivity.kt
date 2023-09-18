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
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val vm: ThemeSwitchViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot(isDarkMode = vm.isDarkTheme)
        }
    }
}

@Composable
fun AppRoot(isDarkMode: Boolean = false) {
    val navController = rememberNavController()
    val router = remember { NavRouter(navController) }

    AppTheme(isDarkMode) {
        CompositionLocalProvider(LocalRouter provides router) {
            NavGraph(navHostController = navController)
        }
    }
}