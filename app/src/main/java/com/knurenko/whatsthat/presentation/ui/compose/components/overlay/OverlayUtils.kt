package com.knurenko.whatsthat.presentation.ui.compose.components.overlay

import android.content.res.Configuration
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel

/**
 * @author Knurenko Bogdan 09.09.2023
 */

data class ObjectPlacementCoordinates(
    val top: Int,
    val left: Int,
    val bottom: Int,
    val right: Int
) {
    val width: Int = right - left
    val height: Int = bottom - top
}

fun calculateObjectPlacement(
    detectedObject: DetectedObjectModel,
    config: Configuration
): ObjectPlacementCoordinates {
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    return ObjectPlacementCoordinates(
        left = (detectedObject.relativeBox.left * screenWidth).toInt(),
        top = (detectedObject.relativeBox.top * screenHeight).toInt(),
        right = (detectedObject.relativeBox.right * screenWidth).toInt(),
        bottom = (detectedObject.relativeBox.bottom * screenHeight).toInt()
    )
}