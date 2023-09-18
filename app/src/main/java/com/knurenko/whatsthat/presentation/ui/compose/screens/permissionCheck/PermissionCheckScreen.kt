package com.knurenko.whatsthat.presentation.ui.compose.screens.permissionCheck

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.knurenko.whatsthat.presentation.navigation.LocalRouter

/**
 * @author Knurenko Bogdan 31.08.2023
 */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionCheckScreen() {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = { PermissionNotGrantedScreen(onRequestRetryClick = { permissionState.launchPermissionRequest() }) },
        permissionNotAvailableContent = { PermissionNotAvailableScreen() }
    ) {
        val router = LocalRouter.current
        SideEffect { router.navigateToCamera() }
    }
}