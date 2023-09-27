package com.knurenko.whatsthat.di

import com.knurenko.whatsthat.presentation.ui.theme.NightModeStorage
import com.knurenko.whatsthat.presentation.ui.theme.ThemeSwitchViewModel
import org.koin.dsl.module

/**
 * @author Knurenko Bogdan 30.08.2023
 */

val appModule = module {
    single { NightModeStorage(context = get()) }
    single { ThemeSwitchViewModel(nightModeStorage = get()) }
}