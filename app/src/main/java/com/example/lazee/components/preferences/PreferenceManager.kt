package com.example.lazee.components.preferences

import android.content.SharedPreferences

object PreferenceKeys {
    // Whether to log wake event on device power on or power on and unlock
    const val WAKE_ON_UNLOCK = "wakeOnUnlock"
    // Earliest time that a shutdown event will be logged
    const val SLEEP_MIN_TIME = "sleepMinTime"
    // Latest time that a shutdown event will be logged
    const val SLEEP_MAX_TIME = "sleepMaxTime"
}

class PreferenceManager(private val sharedPreferences: SharedPreferences) {

    private var _wakeOnUnlock = getBool(PreferenceKeys.WAKE_ON_UNLOCK, true)
    var wakeOnUnlock
        get() = _wakeOnUnlock
        set(value) {
            _wakeOnUnlock = value
            setBool(PreferenceKeys.WAKE_ON_UNLOCK, value)
        }

    private var _shutdownMinTime = getTime(PreferenceKeys.SLEEP_MIN_TIME, Time(21, 0))
    var shutdownMinTime
        get() = _shutdownMinTime
        set(value) {
            _shutdownMinTime = value
            setTime(PreferenceKeys.SLEEP_MIN_TIME, value)
        }

    private var _shutdownMaxTime = getTime(PreferenceKeys.SLEEP_MAX_TIME, Time(9, 0))
    var shutdownMaxTime
        get() = _shutdownMaxTime
        set(value) {
            _shutdownMaxTime = value
            setTime(PreferenceKeys.SLEEP_MAX_TIME, value)
        }

    fun getBool(key: String, default: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    fun setBool(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getTime(key: String, default: Time = Time(0, 0)): Time {
        val string = sharedPreferences.getString(key, "")
        if (string == "" || string == null) {
            return default
        } else {
            return timeFromString(string)
        }
    }

    fun setTime(key: String, value: Time) {
        val string = value.toString()
        sharedPreferences.edit().putString(key, string).apply()
    }

}