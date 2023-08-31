package com.knurenko.whatsthat.presentation.camerax.analyzer

import android.graphics.Rect
import android.util.Log
import com.knurenko.whatsthat.presentation.camerax.utils.AnalysisToRelativeBoundingBoxMapper
import com.google.mlkit.vision.objects.DetectedObject
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.domain.repo.DetectedObjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class OnObjectDetectedListener(
    private val scope: CoroutineScope,
    private val mapper: AnalysisToRelativeBoundingBoxMapper,
    private val detectedObjectRepo: DetectedObjectRepository
) : FrameAnalyzer.ImageProcessorListener {
    override fun onSuccess(
        results: List<DetectedObject>,
        rotation: Int,
        cropRect: Rect,
    ) {
        if (results.isEmpty()) {
            scope.launch {
                detectedObjectRepo.updateObject(null)
            }
            return
        }

        // get absolute coordinates from results and convert to relative + apply rotation
        results.forEach { detectedObject ->
            // draw bounds
            val box = mapper.map(
                originalBox = detectedObject.boundingBox,
                rotationAngle = rotation,
                cropRect = cropRect,
            )

            detectedObject.trackingId?.let { id ->
                scope.launch {
                    val label = detectedObject.labels.firstOrNull()?.text
                    val obj = DetectedObjectModel(id, box, label)
                    detectedObjectRepo.updateObject(obj)
                }
            }
        }
    }

    override fun onFailure(e: Exception) {
        Log.e("OnObjectDetected", "error! ${e.message}")
    }
}