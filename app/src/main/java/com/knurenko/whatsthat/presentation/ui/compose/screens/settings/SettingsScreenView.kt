package com.knurenko.whatsthat.presentation.ui.compose.screens.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.knurenko.whatsthat.R
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme
import com.knurenko.whatsthat.presentation.ui.utils.onClick

/**
 * @author Knurenko Bogdan 21.09.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenView(
    onBackPress: () -> Unit,
    settingsMenuItems: List<SettingsItem>
) {
    Scaffold(
        topBar = { SettingsToolbar(onNavigationBackPress = onBackPress) },
        containerColor = AppTheme.colors.background
    ) {
        LazyColumn(
            modifier = Modifier.padding(
                top = it.calculateTopPadding() + 16.dp,
                start = 8.dp,
                end = 8.dp
            )
        ) {
            items(settingsMenuItems) { menuItem ->
                SettingsItemView(menuItem)
            }
        }
    }
}

@Composable
private fun SettingsToolbar(onNavigationBackPress: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        val (navIcon, title) = createRefs()

        Box(modifier = Modifier
            .constrainAs(navIcon) {
                start.linkTo(parent.start)
                centerVerticallyTo(parent)
            }
            .clip(RoundedCornerShape(45.dp))
            .onClick { onNavigationBackPress.invoke() }
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = "navigation back arrow icon",
                tint = AppTheme.colors.text
            )
        }
        Text(
            modifier = Modifier.constrainAs(title) {
                centerTo(parent)
            },
            text = stringResource(R.string.screens_settings_title),
            color = AppTheme.colors.text,
            fontSize = 24.sp
        )
    }
}

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    SettingsScreenView(
        onBackPress = {},
        settingsMenuItems = listOf(
            SettingsItem(
                title = "Composable settings item",
                currentValue = "current value",
                onClick = {})
        )
    )
}