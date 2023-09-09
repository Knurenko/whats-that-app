package com.knurenko.whatsthat.presentation.ui.compose.components.overlay

import android.content.res.Configuration
import android.graphics.RectF
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.presentation.ui.compose.components.DetectedObjectBorders
import com.knurenko.whatsthat.presentation.ui.compose.components.DetectedObjectCard
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * Camera overlay view, displays detected object borders & small card below
 * @author Knurenko Bogdan 31.08.2023
 */

private const val cardTopMargin = 12
@Composable
fun DetectedObjectOverlay(detectedObject: DetectedObjectModel?) {
    if (detectedObject == null) return

    val objectPlacementCoordinates = calculateObjectPlacement(detectedObject, LocalConfiguration.current)

    Box(modifier = Modifier.fillMaxSize()) {
        DetectedObjectBorders(
            offsetX = objectPlacementCoordinates.left,
            offsetY = objectPlacementCoordinates.top,
            width = objectPlacementCoordinates.width,
            height = objectPlacementCoordinates.height
        )
        DetectedObjectCard(
            offsetX = objectPlacementCoordinates.left,
            offsetY = objectPlacementCoordinates.bottom + cardTopMargin,
            width = objectPlacementCoordinates.width,
            label = detectedObject.label
        )
    }
}


@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    val previewObj = DetectedObjectModel(
        id = 1,
        relativeBox = RectF(
            .2f, .1f, .7f, .34f
        ),
        label = "hello compose!"
    )
    DetectedObjectOverlay(previewObj)
}