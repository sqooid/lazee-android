package com.example.lazee.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.lazee.Constants
import com.example.lazee.components.preferences.PreferenceManager
import com.example.lazee.database.AppDatabase
import com.example.lazee.utils.writeToLog

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        if (intent.action !in listOf(
                Intent.ACTION_BOOT_COMPLETED,
                Intent.ACTION_LOCKED_BOOT_COMPLETED
            )
        ) {
            writeToLog(context, "Received other broadcast in boot receiver")
        }

        val appContext = context.applicationContext
        val sharedPreferences =
            appContext.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
        val preferenceManager = PreferenceManager(sharedPreferences)

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            if (!preferenceManager.wakeOnUnlock) {
                writeToLog(appContext, "Received completed boot broadcast, skipping")
                return
            }
            writeToLog(appContext, "Received completed boot broadcast")
        } else if (intent.action == Intent.ACTION_LOCKED_BOOT_COMPLETED) {
            if (preferenceManager.wakeOnUnlock) {
                writeToLog(appContext, "Received locked boot broadcast, skipping")
                return
            }
            writeToLog(appContext, "Received locked boot broadcast")
        }

        val db = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
            .fallbackToDestructiveMigration().build()

        writeToLog(context,"Registered shutdown receiver")
        val br = ShutdownReceiver()
        val filter = IntentFilter(Intent.ACTION_SHUTDOWN)
        ContextCompat.registerReceiver(
            context.applicationContext,
            br,
            filter,
            ContextCompat.RECEIVER_EXPORTED
        )
    }
}