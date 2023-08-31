package com.knurenko.whatsthat.presentation.ui.compose.screens.permissionCheck

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knurenko.whatsthat.R
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 31.08.2023
 */

@Composable
fun PermissionNotGrantedScreen(onRequestRetryClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(128.dp)
                    .align(CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_permission_error),
                contentDescription = "permission not granted error icon",
                tint = AppTheme.colors.error
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.permission_not_granted_error),
                color = AppTheme.colors.error,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            OutlinedButton(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 24.dp),
                onClick = onRequestRetryClick,
                border = BorderStroke(width = 2.dp, color = AppTheme.colors.accent)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 16.dp),
                    text = stringResource(R.string.permission_request_again),
                    color = AppTheme.colors.accent,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Preview(name = "light")
@Preview(name = "dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() = AppTheme {
    PermissionNotGrantedScreen()
}