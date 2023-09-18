@file:OptIn(ExperimentalMaterial3Api::class)

package com.knurenko.whatsthat.presentation.ui.compose.components.drawer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.knurenko.whatsthat.R
import com.knurenko.whatsthat.presentation.navigation.LocalRouter
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 18.09.2023
 */

@Composable
fun NavigationDrawerContent() {
    val router = LocalRouter.current
    ModalDrawerSheet(drawerContainerColor = AppTheme.colors.background) {
        GalleryItem { router.navigateToGallery() }
        Settings { router.navigateToSettings() }
    }
}

 

@Composable
private fun MutualItem(title: String, iconResId: Int, onClick: () -> Unit) {
    val itemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = AppTheme.colors.accent.copy(alpha = .3f),
        unselectedContainerColor = AppTheme.colors.background
    )
    NavigationDrawerItem(
        label = { Text(text = title, color = AppTheme.colors.text) },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
        colors = itemColors,
        icon = {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = "$title icon",
                tint = AppTheme.colors.text
            )
        }
    )
    Divider()
}

@Composable
private fun GalleryItem(onClick: () -> Unit) = MutualItem(title = "Image gallery", iconResId = R.drawable.ic_gallery, onClick)

@Composable
private fun Settings (onClick: () -> Unit) = MutualItem(title = "Settings", iconResId = R.drawable.ic_settings, onClick)

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    NavigationDrawerContent()
}