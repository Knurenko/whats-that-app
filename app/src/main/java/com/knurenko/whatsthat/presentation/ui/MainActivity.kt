package com.knurenko.whatsthat.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.knurenko.whatsthat.presentation.ui.compose.screens.cameraView.CameraViewScreen
import com.knurenko.whatsthat.presentation.ui.compose.screens.permissionCheck.PermissionCheckScreen
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
fun AppRoot(
    isDarkMode: Boolean = false
) {
    AppTheme(isDarkMode) {
        PermissionCheckScreen {
            CameraViewScreen()
        }
    }
}