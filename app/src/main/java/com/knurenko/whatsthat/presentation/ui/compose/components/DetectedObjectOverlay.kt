package com.knurenko.whatsthat.presentation.ui.compose.components

import android.content.res.Configuration
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knurenko.whatsthat.domain.entity.DetectedObjectModel
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 31.08.2023
 */

private val borderCornerRadius = 12.dp
private val borderStrokeWidth = 2.dp

@OptIn(ExperimentalTextApi::class)
@Composable
fun DetectedObjectOverlay(obj: DetectedObjectModel) {
    val accentColor = AppTheme.colors.accent
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas {
            val width = size.width
            val height = size.height

            val bounds = RectF(
                obj.relativeBox.left * width,
                obj.relativeBox.top * height,
                obj.relativeBox.right * width,
                obj.relativeBox.bottom * height
            )

            translate(left = bounds.left, top = bounds.top) {
                val boundsHeight = bounds.bottom - bounds.top
                val boundsSize = Size(bounds.right - bounds.left, boundsHeight)

                // visible bounds of the object
                drawRoundRect(
                    color = accentColor,
                    size = boundsSize,
                    cornerRadius = CornerRadius(borderCornerRadius.toPx(), borderCornerRadius.toPx()),
                    style = Stroke(width = borderStrokeWidth.toPx())
                )

                val textLayoutResult = textMeasurer.measure(
                    text = AnnotatedString(obj.label ?: "undefined object"),
                    style = TextStyle(
                        color = accentColor,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    constraints = Constraints(minWidth = 10, maxWidth = boundsSize.width.toInt())
                )
                val textSize = textLayoutResult.size

                // label of object
                translate(top = boundsHeight + 10) {
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(x = (boundsSize.width - textSize.width) / 2f, y = 0f)
                    )
                }
            }
        }

    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    val previewObj = DetectedObjectModel(
        id = 1,
        relativeBox = RectF(
            .2f, .1f, .7f, .34f
        ),
        label = "hello compose!"
    )
    DetectedObjectOverlay(previewObj)
}