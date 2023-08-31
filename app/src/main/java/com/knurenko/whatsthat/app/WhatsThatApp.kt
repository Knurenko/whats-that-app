package com.knurenko.whatsthat.app

import android.app.Application
import com.knurenko.whatsthat.di.appModule
import com.knurenko.whatsthat.di.cameraUsageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Knurenko Bogdan 30.08.2023
 */
class WhatsThatApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WhatsThatApp)
            modules(appModule, cameraUsageModule)
        }
    }
}