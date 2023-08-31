package com.knurenko.whatsthat.domain.repo

import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import kotlinx.coroutines.flow.StateFlow

/**
 * repository to store flow of detected object and update its value
 * @author Knurenko Bogdan 31.08.2023
 */
interface DetectedObjectRepository {
    suspend fun updateObject(detectedObjectModel: DetectedObjectModel?)
    fun getObjectFlow(): StateFlow<DetectedObjectModel?>
}