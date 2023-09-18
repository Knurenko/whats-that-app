package com.knurenko.whatsthat.presentation.navigation

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * reference to [NavRouter] to access from composable
 * @author Knurenko Bogdan 18.09.2023
 */

val LocalRouter = staticCompositionLocalOf<NavRouter> { error("NavRouter isn't initialized!") }