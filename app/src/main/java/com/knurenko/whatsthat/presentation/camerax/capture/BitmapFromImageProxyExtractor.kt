package com.knurenko.whatsthat.presentation.camerax.capture

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.RectF
import android.util.Size
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.knurenko.whatsthat.presentation.camerax.utils.RelativeToAbsoluteBoundingBoxMapper
import com.google.mlkit.vision.common.InputImage
import com.knurenko.whatsthat.presentation.camerax.utils.cropToRect

class BitmapFromImageProxyExtractor(
    private val relativeToAbsoluteBoundingBoxMapper: RelativeToAbsoluteBoundingBoxMapper
) {

    @ExperimentalGetImage
    fun extractBitmap(imageProxy: ImageProxy, relativeCropRect: RectF): Bitmap? {
        val rotationDegrees = imageProxy.imageInfo.rotationDegrees

        fun applyIncrease(box: RectF, factor: Float): RectF =
            RectF(
                box.left - factor,
                box.top - factor,
                box.right + factor,
                box.bottom + factor
            )

        fun getTotalCropRect(): Rect {
            val cropRect = imageProxy.cropRect

            val increasedBox = applyIncrease(relativeCropRect, .05f)
            val relativeBoxWithInverseRotation = applyInverseRotation(increasedBox, rotationDegrees)
            val croppedBox = relativeToAbsoluteBoundingBoxMapper.map(
                relativeBoxWithInverseRotation,
                Size(cropRect.width(), cropRect.height())
            )

            val left = croppedBox.left.toInt() + cropRect.left
            val top = croppedBox.top.toInt() + cropRect.top
            val right = croppedBox.right.toInt() + cropRect.left
            val bottom = croppedBox.bottom.toInt() + cropRect.top

            return Rect(left, top, right, bottom)
        }

        val originalBitmap = imageProxy.image?.let { image ->
            val inputImage =
                InputImage.fromMediaImage(image, 0)
            return@let inputImage.bitmapInternal?.cropToRect(getTotalCropRect())
        }

        return originalBitmap?.let { bitmap ->
            val matrix = Matrix()
            matrix.postRotate(rotationDegrees.toFloat())

            return@let Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
    }

    private fun applyInverseRotation(oldBox: RectF, angle: Int): RectF {
        val left = when (angle) {
            90 -> oldBox.top
            180 -> (1 - oldBox.right)
            270 -> (1 - oldBox.bottom)
            else -> oldBox.left
        }
        val top = when (angle) {
            90 -> (1 - oldBox.right)
            180 -> (1 - oldBox.bottom)
            270 -> oldBox.left
            else -> oldBox.top
        }
        val right = when (angle) {
            90 -> oldBox.bottom
            180 -> (1 - oldBox.left)
            270 -> (1 - oldBox.top)
            else -> oldBox.right
        }
        val bottom = when (angle) {
            90 -> (1 - oldBox.left)
            180 -> (1 - oldBox.top)
            270 -> oldBox.right
            else -> oldBox.bottom
        }
        return RectF(left, top, right, bottom)
    }
}