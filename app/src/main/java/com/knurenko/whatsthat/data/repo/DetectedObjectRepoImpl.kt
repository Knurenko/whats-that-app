package com.knurenko.whatsthat.data.repo

import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.domain.repo.DetectedObjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author Knurenko Bogdan 31.08.2023
 */

class DetectedObjectRepositoryImpl : DetectedObjectRepository {
    private val detectedObjectModelFlow: MutableStateFlow<DetectedObjectModel?> = MutableStateFlow(null)
    override suspend fun updateObject(detectedObjectModel: DetectedObjectModel?) {
        detectedObjectModelFlow.emit(detectedObjectModel)
    }

    override fun getObjectFlow(): StateFlow<DetectedObjectModel?> = detectedObjectModelFlow.asStateFlow()
}