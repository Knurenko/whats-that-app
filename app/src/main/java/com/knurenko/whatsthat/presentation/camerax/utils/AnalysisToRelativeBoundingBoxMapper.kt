package com.knurenko.whatsthat.presentation.camerax.utils

import android.graphics.Rect
import android.graphics.RectF

/**
 * Maps Rect from analysis ImageProxy coordinate system into RectF with screen-size-related ratios
 */
class AnalysisToRelativeBoundingBoxMapper {

    /**
     * @param originalBox detected object's bounding box
     *
     * @param rotationAngle analysis's imageProxy rotation angle
     * (degrees image should be rotated to fit the displayed one)
     *
     * @param cropRect analysis_imageProxy's cropRect
     * (area of image to be displayed, which should be cropped to fit the screen
     * so we need to change our coordinate's system to match it)
     */
    fun map(
        originalBox: Rect,
        rotationAngle: Int,
        cropRect: Rect,
    ): RectF {
        fun applyCrop(): Rect {
            /** apply cropRect left & top offsets */
            val left = originalBox.left - cropRect.left
            val top = originalBox.top - cropRect.top
            val right = originalBox.right - cropRect.left
            val bottom = originalBox.bottom - cropRect.top

            return Rect(left, top, right, bottom)
        }

        /** convert absolute coordinates into relative */
        fun convertToRelative(croppedBox: Rect): RectF {
            val w = cropRect.width()
            val h = cropRect.height()

            val left = croppedBox.left.toFloat() / w
            val top = croppedBox.top.toFloat() / h
            val right = croppedBox.right.toFloat() / w
            val bottom = croppedBox.bottom.toFloat() / h

            return RectF(left, top, right, bottom)
        }

        /** apply rotation to that coordinates */
        fun applyRotation(relativeBox: RectF): RectF {
            val left = when (rotationAngle) {
                90 -> (1 - relativeBox.bottom)
                180 -> (1 - relativeBox.right)
                270 -> (relativeBox.top)
                /* 0 */ else -> relativeBox.left
            }

            val top = when (rotationAngle) {
                90 -> relativeBox.left
                180 -> (1 - relativeBox.bottom)
                270 -> (1 - relativeBox.right)
                /* 0 */ else -> relativeBox.top
            }

            val right = when (rotationAngle) {
                90 -> (1 - relativeBox.top)
                180 -> (1 - relativeBox.left)
                270 -> relativeBox.bottom
                /* 0 */ else -> relativeBox.right
            }

            val bottom = when (rotationAngle) {
                90 -> relativeBox.right
                180 -> (1 - relativeBox.top)
                270 -> (1 - relativeBox.left)
                /* 0 */ else -> relativeBox.bottom
            }

            return RectF(left, top, right, bottom)
        }

        return applyRotation(convertToRelative(applyCrop()))
    }
}