package com.knurenko.whatsthat.presentation.ui.compose.components.overlay

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knurenko.whatsthat.R
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 08.09.2023
 */

@Composable
fun DetectedObjectCard(
    offsetX: Int,
    offsetY: Int,
    width: Int,
    label: String?,
    onAnalyzePress: () -> Unit = {}
) {
    val visibleLabel = label ?: stringResource(id = R.string.object_label_null)
    Column(
        modifier = Modifier
            .absoluteOffset(x = offsetX.dp, y = offsetY.dp)
            .width(width.dp)
            .background(color = AppTheme.colors.background, shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = visibleLabel,
            style = TextStyle(
                color = AppTheme.colors.accent,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)
        )

        Button(
            onClick = onAnalyzePress,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.accent,
                contentColor = AppTheme.colors.background
            )
        ) {
            Text(text = "Google me!")
        }
    }

}

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    DetectedObjectCard(width = 150, label = "hello compose", offsetX = 0, offsetY = 20)
}