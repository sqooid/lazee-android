package com.example.lazee.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.lazee.database.AppDatabase
import com.example.lazee.database.SleepEvent
import java.util.Date

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        if (intent.action !in listOf(
                Intent.ACTION_BOOT_COMPLETED,
                Intent.ACTION_LOCKED_BOOT_COMPLETED
            )
        ) return

        Log.d("test", "Received boot")
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
            .fallbackToDestructiveMigration().build()
        val dao = db.sleepEventDao()
        val events = dao.getAll()
        dao.insert(SleepEvent(0, Date(), Date()))
        Log.d("test", "Sleep events: $events")

        val br = BootReceiver()
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        ContextCompat.registerReceiver(context, br, filter, ContextCompat.RECEIVER_EXPORTED)
    }
}