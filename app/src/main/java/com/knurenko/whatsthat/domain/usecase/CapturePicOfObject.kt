package com.knurenko.whatsthat.domain.usecase

import com.knurenko.whatsthat.domain.entity.DetectedObjectModel

/**
 * useCase to capture image of detected object
 * @author Knurenko Bogdan 31.08.2023
 */
interface CapturePicOfObject {
    suspend fun invoke(detectedObject: DetectedObjectModel): ByteArray?
}