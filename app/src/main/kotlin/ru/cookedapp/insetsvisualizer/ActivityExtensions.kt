package ru.cookedapp.insetsvisualizer

import android.app.Activity
import android.content.res.Configuration

internal val Activity.isDarkModeEnabled: Boolean
    get() = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES