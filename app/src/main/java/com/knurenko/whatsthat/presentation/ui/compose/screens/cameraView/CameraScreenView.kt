package com.knurenko.whatsthat.presentation.ui.compose.screens.cameraView

import android.content.res.Configuration
import android.graphics.RectF
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.presentation.ui.compose.components.overlay.DetectedObjectOverlay
import com.knurenko.whatsthat.presentation.ui.compose.components.toolbar.CameraToolbar
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 18.09.2023
 */

@Composable
fun CameraScreenView(
    objectToHighlight: DetectedObjectModel?,
    onMenuClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    CameraToolbar(onMenuClick = onMenuClick, onGalleryClick = onGalleryClick)
    DetectedObjectOverlay(detectedObject = objectToHighlight)
}

@Preview(name = "light")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    val toDisplay = DetectedObjectModel(1, RectF(.1f, .1f, .6f, .4f), "hello compose")
    CameraScreenView(toDisplay, {}, {})
}