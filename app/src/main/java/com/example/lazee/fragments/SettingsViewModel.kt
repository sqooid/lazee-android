package com.example.lazee.fragments

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.lazee.Constants
import com.example.lazee.components.preferences.PreferenceManager


class SettingsViewModel(app: Application) : AndroidViewModel(app) {

    private val sharedPreferences =
        app.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)

    val _preferenceManager = PreferenceManager(sharedPreferences)
    val preferenceManager get() = _preferenceManager

}