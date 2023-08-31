package com.knurenko.whatsthat.presentation.ui.compose.screens.cameraView

import androidx.lifecycle.ViewModel
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.domain.repo.DetectedObjectRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Knurenko Bogdan 31.08.2023
 */
class CameraViewModel(
    detectedObjectRepository: DetectedObjectRepository
) : ViewModel() {
    val detectedObject: Flow<DetectedObjectModel?> = detectedObjectRepository.getObjectFlow()
}