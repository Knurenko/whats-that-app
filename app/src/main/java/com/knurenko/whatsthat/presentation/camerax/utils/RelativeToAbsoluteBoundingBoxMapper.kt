package com.knurenko.whatsthat.presentation.camerax.utils

import android.graphics.RectF
import android.util.Size

class RelativeToAbsoluteBoundingBoxMapper {

    fun map(relativeBox: RectF, viewSize: Size): RectF {
        val left = relativeBox.left * viewSize.width
        val top = relativeBox.top * viewSize.height
        val right = relativeBox.right * viewSize.width
        val bottom = relativeBox.bottom * viewSize.height

        return RectF(left, top, right, bottom)
    }
}