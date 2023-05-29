package com.example.lazee

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.lazee.receivers.BootReceiver
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.lazee", appContext.packageName)
    }

    @Test
    fun receiveBootBroadcast() {
        val br = BootReceiver()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(Intent.ACTION_BOOT_COMPLETED)
        br.onReceive(context, intent)
//        val filter=IntentFilter(Intent.ACTION_BOOT_COMPLETED)
//        context.registerReceiver(br,filter,ContextCompat.RECEIVER_EXPORTED)
    }
}