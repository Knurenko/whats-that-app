package com.knurenko.whatsthat.di.camerax

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888
import com.knurenko.whatsthat.presentation.camerax.analyzer.FrameAnalyzer
import java.util.concurrent.ExecutorService

object FrameAnalyzerFactory {
    fun create(
        executorService: ExecutorService,
        onObjectDetectedListener: FrameAnalyzer.ImageProcessorListener,
    ) =
        ImageAnalysis.Builder()
            .setOutputImageFormat(OUTPUT_IMAGE_FORMAT_YUV_420_888)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(
                    executorService,
                    FrameAnalyzer(
                        listener = onObjectDetectedListener,
                    )
                )
            }
}