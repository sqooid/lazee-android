package com.example.lazee.utils

import android.content.Context
import android.util.Log
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.TimeZone


fun writeToLog(context: Context, text: String) {
    val logFilePath = "app-log.txt"
    val timestamp = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(TimeZone.getDefault().toZoneId()).format(Instant.now())
    Log.d("app", text)
    context.openFileOutput(logFilePath, Context.MODE_PRIVATE or Context.MODE_APPEND).use {
        it.write("[$timestamp] ${text}\n".toByteArray())
    }
}
