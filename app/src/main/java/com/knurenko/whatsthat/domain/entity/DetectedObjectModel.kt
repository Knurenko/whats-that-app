package com.knurenko.whatsthat.domain.entity

import android.graphics.RectF

/**
 * Entity to represent detected object on the screen
 *
 * @param relativeBox Square part of the screen, where is located detected object
 * each coordinate lays in [0..1] and is value, by which we should multiply w/h of container's view to get exact view borders
 *
 * @param label Detected object's label with highest match coefficient (result from ML kit)
 *
 * @author Knurenko Bogdan 31.08.2023
 */
data class DetectedObjectModel(
    val id: Int,
    val relativeBox: RectF,
    val label: String?
)