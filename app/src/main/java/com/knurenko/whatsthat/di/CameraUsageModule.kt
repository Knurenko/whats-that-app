package com.knurenko.whatsthat.di

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import com.knurenko.whatsthat.data.repo.DetectedObjectRepositoryImpl
import com.knurenko.whatsthat.di.camerax.FrameAnalyzerFactory
import com.knurenko.whatsthat.domain.repo.DetectedObjectRepository
import com.knurenko.whatsthat.domain.usecase.CapturePicOfObject
import com.knurenko.whatsthat.presentation.camerax.analyzer.OnObjectDetectedListener
import com.knurenko.whatsthat.presentation.camerax.capture.CapturePicOfObjectImpl
import com.knurenko.whatsthat.di.camerax.ImageCaptureFactory
import com.knurenko.whatsthat.presentation.camerax.capture.BitmapFromImageProxyExtractor
import com.knurenko.whatsthat.di.camerax.PreviewFactory
import com.knurenko.whatsthat.presentation.camerax.utils.AnalysisToRelativeBoundingBoxMapper
import com.knurenko.whatsthat.presentation.camerax.utils.RelativeToAbsoluteBoundingBoxMapper
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author Knurenko Bogdan 31.08.2023
 */

val cameraUsageModule = module {
    // mappers
    single { AnalysisToRelativeBoundingBoxMapper() }
    single { RelativeToAbsoluteBoundingBoxMapper() }

    // special
    single {
        BitmapFromImageProxyExtractor(
            relativeToAbsoluteBoundingBoxMapper = get()
        )
    }
    single<OnObjectDetectedListener> { (scope: CoroutineScope) -> OnObjectDetectedListener(
        scope = scope,
        detectedObjectRepo = get(),
        mapper = get()
    ) }
    single<ExecutorService> { Executors.newSingleThreadExecutor() }
    single<CapturePicOfObject> {
        CapturePicOfObjectImpl(
            imageCapture = get(),
            cameraExecutor = get(),
            bitmapExtractor = get()
        )
    }

    // camerax useCases
    single<ImageCapture> { ImageCaptureFactory.create() }
    single<Preview> { (previewView: PreviewView) -> PreviewFactory.create(previewView = previewView) }
    single<ImageAnalysis> { FrameAnalyzerFactory.create(
        executorService = get(),
        onObjectDetectedListener = get(),
    ) }

    // repo
    single<DetectedObjectRepository> { DetectedObjectRepositoryImpl() }
}