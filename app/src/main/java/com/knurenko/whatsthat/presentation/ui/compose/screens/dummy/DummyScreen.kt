package com.knurenko.whatsthat.presentation.ui.compose.screens.dummy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * @author Knurenko Bogdan 18.09.2023
 */

@Composable
fun DummyScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text, modifier = Modifier.align(Alignment.Center))
    }
}