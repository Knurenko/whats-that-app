package com.knurenko.whatsthat.presentation.camerax.analyzer

import android.graphics.Rect
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.knurenko.whatsthat.di.detection.ObjectDetectorFactory

class FrameAnalyzer(
    private val listener: ImageProcessorListener,
) : ImageAnalysis.Analyzer {

    interface ImageProcessorListener {
        fun onSuccess(
            results: List<DetectedObject>,
            rotation: Int,
            cropRect: Rect,
        )

        fun onFailure(e: Exception)
    }

    private val detector by lazy { ObjectDetectorFactory.create() }

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image?.let { mediaImage ->
            val image = InputImage.fromMediaImage(mediaImage, 0)

            val cropRect = imageProxy.cropRect
            val rotation = imageProxy.imageInfo.rotationDegrees

            // Pass image to an ML Kit Vision API
            detector.process(image)
                .addOnSuccessListener {
                    listener.onSuccess(
                        results = it,
                        rotation = rotation,
                        cropRect = cropRect,
                    )
                }
                .addOnFailureListener { listener.onFailure(it) }
                .addOnCompleteListener { imageProxy.close() }
        }
    }
}