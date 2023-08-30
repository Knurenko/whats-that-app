package com.knurenko.whatsthat

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knurenko.whatsthat.ui.theme.AppTheme
import com.knurenko.whatsthat.ui.theme.ThemeSwitchViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val vm: ThemeSwitchViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot(isDarkMode = vm.isDarkTheme, onClick = { vm.toggleTheme() })
        }
    }
}

@Composable
fun AppRoot(
    isDarkMode: Boolean = false,
    onClick: () -> Unit = {}
) {
    AppTheme(isDarkMode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(16.dp),
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colors.accent,
                        contentColor = AppTheme.colors.background
                    )
                ) {
                    Text(text = "Change theme", style = TextStyle(fontSize = 20.sp))
                }
            }
        }
    }
}

@Preview(name = "Light theme")
@Composable
private fun Preview() {
    AppRoot()
}


@Preview(name = "Dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    AppRoot(isDarkMode = true)
}