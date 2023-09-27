@file:OptIn(ExperimentalMaterial3Api::class)

package com.knurenko.whatsthat.presentation.ui.compose.components.drawer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knurenko.whatsthat.R
import com.knurenko.whatsthat.presentation.navigation.NavRouter
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme
import com.knurenko.whatsthat.presentation.ui.utils.onClick

/**
 * @author Knurenko Bogdan 18.09.2023
 */

@Composable
fun NavigationDrawerContent(router: NavRouter?) {
    ModalDrawerSheet(drawerContainerColor = AppTheme.colors.background) {
        GalleryItem { router?.navigateToGallery() }
        Settings { router?.navigateToSettings() }
    }
}

@Composable
private fun MutualItem(title: String, iconResId: Int, onClick: () -> Unit) =
    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .onClick { onClick() }
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = "$title icon",
                modifier = Modifier
                    .align(CenterVertically)
                    .size(24.dp),
                tint = AppTheme.colors.text
            )
            Text(
                text = title,
                color = AppTheme.colors.text,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Divider()
    }

@Composable
private fun GalleryItem(onClick: () -> Unit) =
    MutualItem(title = "Image gallery", iconResId = R.drawable.ic_gallery, onClick)

@Composable
private fun Settings(onClick: () -> Unit) =
    MutualItem(title = "Settings", iconResId = R.drawable.ic_settings, onClick)

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    NavigationDrawerContent(null)
}