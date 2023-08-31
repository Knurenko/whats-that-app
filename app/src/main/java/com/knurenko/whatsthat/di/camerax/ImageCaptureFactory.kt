package com.knurenko.whatsthat.di.camerax

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY

object ImageCaptureFactory {
    fun create() = ImageCapture.Builder()
        .setCaptureMode(CAPTURE_MODE_MINIMIZE_LATENCY)
        .build()
}