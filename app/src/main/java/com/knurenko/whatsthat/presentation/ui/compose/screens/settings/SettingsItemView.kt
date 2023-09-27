package com.knurenko.whatsthat.presentation.ui.compose.screens.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme
import com.knurenko.whatsthat.presentation.ui.utils.onClick

/**
 * @author Knurenko Bogdan 21.09.2023
 */

data class SettingsItem(val title: String, val currentValue: String?, val onClick: () -> Unit)

@Composable
fun SettingsItemView(settingsItem: SettingsItem) =
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .onClick { settingsItem.onClick.invoke() }
        .padding(8.dp))
    {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = settingsItem.title,
            style = TextStyle(
                color = AppTheme.colors.text,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        settingsItem.currentValue?.let {
            Text(
                text = it, modifier = Modifier.align(Alignment.CenterVertically),
                style = TextStyle(color = AppTheme.colors.text.copy(alpha = .4f), fontSize = 16.sp)
            )
        }
    }

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    SettingsItemView(
        settingsItem = SettingsItem(
            title = "Composable settings item",
            currentValue = "current value",
            onClick = {})
    )
}