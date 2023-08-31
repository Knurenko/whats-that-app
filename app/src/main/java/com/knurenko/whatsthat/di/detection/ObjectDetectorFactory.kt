package com.knurenko.whatsthat.di.detection

import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions

/**
 *
 * @author Knurenko Bogdan 31.08.2023
 */
object ObjectDetectorFactory {
    private val model: LocalModel by lazy {
        LocalModel.Builder()
            .setAssetFilePath("model.tflite")
            .build()
    }

    private val options by lazy {
        CustomObjectDetectorOptions.Builder(model)
            .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
            .enableClassification()
            .build()
    }

    fun create() = ObjectDetection.getClient(options)
}