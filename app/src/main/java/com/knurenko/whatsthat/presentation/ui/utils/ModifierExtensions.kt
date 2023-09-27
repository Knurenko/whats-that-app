package com.knurenko.whatsthat.presentation.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.knurenko.whatsthat.presentation.ui.theme.AppTheme

/**
 * @author Knurenko Bogdan 22.09.2023
 */

inline fun Modifier.onClick(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = rememberRipple(color = AppTheme.colors.shadow),
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}