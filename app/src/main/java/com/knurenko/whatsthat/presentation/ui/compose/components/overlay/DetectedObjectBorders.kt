package com.knurenko.whatsthat.presentation.ui.compose.components.overlay

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 09.09.2023
 */

private val borderCornerRadius = 12.dp
private val borderStrokeWidth = 2.dp

/**
 * Visible corners of detected object
 */
@Composable
fun DetectedObjectBorders(offsetX: Int, offsetY: Int, width: Int, height: Int) {
    Box(
        modifier = Modifier
            .absoluteOffset(x = offsetX.dp, y = offsetY.dp)
            .width(width.dp)
            .height(height.dp)
            .border(
                border = BorderStroke(
                    width = borderStrokeWidth,
                    color = AppTheme.colors.accent
                ), shape = RoundedCornerShape(borderCornerRadius)
            )
    )
}

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    DetectedObjectBorders(
        offsetX = 54,
        offsetY = 32,
        width = 140,
        height = 340
    )
}