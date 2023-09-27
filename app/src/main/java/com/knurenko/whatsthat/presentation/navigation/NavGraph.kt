package com.knurenko.whatsthat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.knurenko.whatsthat.presentation.ui.compose.screens.cameraView.CameraScreenController
import com.knurenko.whatsthat.presentation.ui.compose.screens.dummy.DummyScreen
import com.knurenko.whatsthat.presentation.ui.compose.screens.permissionCheck.PermissionCheckScreen
import com.knurenko.whatsthat.presentation.ui.compose.screens.settings.SettingsScreenController

/**
 * @author Knurenko Bogdan 18.09.2023
 */

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.PERMISSION_CHECK) {
        composable(Screens.PERMISSION_CHECK) {
            PermissionCheckScreen()
        }

        composable(Screens.CAMERA) {
            CameraScreenController()
        }

        composable(Screens.GALLERY) {
            DummyScreen(text = "Gallery screen")
        }

        composable(Screens.SETTINGS) {
            SettingsScreenController()
        }
    }
}


