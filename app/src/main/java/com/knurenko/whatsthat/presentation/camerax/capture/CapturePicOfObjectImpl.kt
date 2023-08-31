package com.knurenko.whatsthat.presentation.camerax.capture

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.domain.usecase.CapturePicOfObject
import com.knurenko.whatsthat.presentation.camerax.utils.toByteArray
import java.util.concurrent.ExecutorService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CapturePicOfObjectImpl(
    private val imageCapture: ImageCapture,
    private val cameraExecutor: ExecutorService,
    private val bitmapExtractor: BitmapFromImageProxyExtractor
) : CapturePicOfObject {
    override suspend fun invoke(detectedObject: DetectedObjectModel): ByteArray? =
        suspendCoroutine { continuation ->
            imageCapture.takePicture(
                cameraExecutor,
                @ExperimentalGetImage object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(proxy: ImageProxy) {
                        val bytes = bitmapExtractor.extractBitmap(
                            imageProxy = proxy,
                            relativeCropRect = detectedObject.relativeBox
                        )?.toByteArray()
                        proxy.close()
                        continuation.resume(bytes)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        continuation.resume(null)
                    }
                })
        }
}