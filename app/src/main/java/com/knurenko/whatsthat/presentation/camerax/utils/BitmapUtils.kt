package com.knurenko.whatsthat.presentation.camerax.utils

import android.graphics.Bitmap
import android.graphics.Rect
import java.io.ByteArrayOutputStream
import java.lang.IllegalArgumentException

/**
 * @author Knurenko Bogdan 31.08.2023
 */

private fun extractArea(bmp: Bitmap, left: Int, top: Int, width: Int, height: Int): Bitmap? {
    val x = if (left > 0) left else 0
    val y = if (top > 0) top else 0
    val w =
        if (x + width <= bmp.width) width else (bmp.width - left)
    val h =
        if (top + height <= bmp.height) height else (bmp.height - top)

    return try {
        Bitmap.createBitmap(bmp, x, y, w, h)
    } catch (e: IllegalArgumentException) {
        null
    }
}

fun Bitmap.cropToRect(rect: Rect): Bitmap? {
    return extractArea(this, rect.left, rect.top, rect.width(), rect.height())
}

fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, stream)

    return stream.toByteArray()
}