package com.knurenko.whatsthat.di

import com.knurenko.whatsthat.ui.theme.ThemeSwitchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Knurenko Bogdan 30.08.2023
 */

val appModule = module {
    viewModel { ThemeSwitchViewModel() }
}