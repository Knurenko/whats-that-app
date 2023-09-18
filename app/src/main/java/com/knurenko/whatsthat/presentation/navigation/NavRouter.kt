package com.knurenko.whatsthat.presentation.navigation

import androidx.navigation.NavHostController

/**
 * class to perform navigation
 * instance should be provided via staticCompositionLocal
 * @author Knurenko Bogdan 18.09.2023
 */
class NavRouter(private val navController: NavHostController) {

    fun goBack() {
        navController.popBackStack()
    }

    fun navigateToCamera() {
        navController.navigate(Screens.CAMERA) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    fun navigateToSettings() {
        navController.navigate(Screens.SETTINGS)
    }

    fun navigateToGallery() {
        navController.navigate(Screens.GALLERY)
    }
}
