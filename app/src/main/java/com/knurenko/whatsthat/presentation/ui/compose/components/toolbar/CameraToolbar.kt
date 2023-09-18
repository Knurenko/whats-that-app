package com.knurenko.whatsthat.presentation.ui.compose.components.toolbar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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

/**
 * @author Knurenko Bogdan 18.09.2023
 */

@Composable
fun CameraToolbar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        val (title, menuIcon, galleryIcon) = createRefs()
        Box(modifier = Modifier
            .constrainAs(menuIcon) {
                start.linkTo(parent.start)
                centerVerticallyTo(parent)
            }
            .clip(RoundedCornerShape(45.dp))
            .clickable { onMenuClick.invoke() }
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(R.drawable.ic_drawer_menu),
                contentDescription = "drawer menu icon",
                tint = AppTheme.colors.text
            )
        }


        Text(
            modifier = Modifier.constrainAs(title) {
                centerTo(parent)
            },
            text = stringResource(id = R.string.app_name),
            color = AppTheme.colors.text,
            fontSize = 24.sp
        )

        Box(modifier = Modifier
            .constrainAs(galleryIcon) {
                end.linkTo(parent.end)
                centerVerticallyTo(parent)
            }
            .clip(RoundedCornerShape(45.dp))
            .clickable { onGalleryClick.invoke() }
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(R.drawable.ic_gallery),
                contentDescription = "gallery icon",
                tint = AppTheme.colors.text
            )
        }
    }
}

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    CameraToolbar(onMenuClick = {}, onGalleryClick = {})
}