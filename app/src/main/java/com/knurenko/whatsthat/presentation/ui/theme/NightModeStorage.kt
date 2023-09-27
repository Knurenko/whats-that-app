package com.knurenko.whatsthat.presentation.ui.theme

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration


/**
 * @author Knurenko Bogdan 31.08.2023
 */
class NightModeStorage(context: Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("NIGHT_MODE_PREFS", Context.MODE_PRIVATE)
    }

    var isNightModeSelected: Boolean
        get() = prefs.getBoolean(NIGHT_MODE_KEY, initialNightMode)
        set(value) = with(prefs.edit()) {
            putBoolean(NIGHT_MODE_KEY, value)
            apply()
        }

    private val initialNightMode by lazy {
        val nightModeFlags: Int = context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }

    private companion object {
        const val NIGHT_MODE_KEY = "is night mode enabled"
    }
}