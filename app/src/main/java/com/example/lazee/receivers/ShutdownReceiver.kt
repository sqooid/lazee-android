package com.example.lazee.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.lazee.utils.writeToLog

class ShutdownReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context==null || intent==null) return
        if (intent.action != Intent.ACTION_SHUTDOWN){
            writeToLog(context,"Received other broadcast in shutdown receiver")
            return
        }

        writeToLog(context,"Received shutdown broadcast")
    }
}
