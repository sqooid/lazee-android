package com.example.lazee

import com.example.lazee.components.preferences.Time
import com.example.lazee.components.preferences.timeFromString
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TimeUnitTest {
    @Test
    fun timeStringConversionCorrect() {
        val time = Time(13, 5)
        val timeString = time.toString()
        val backTime = timeFromString(timeString)
        assertEquals(time, backTime)
    }

    @Test
    fun timeString12HourCorrect() {
        var time = Time(0, 5)
        assertEquals(time.to12HourString(), "12:05 AM")
        time = Time(11, 5)
        assertEquals(time.to12HourString(), "11:05 AM")
        time = Time(12, 5)
        assertEquals(time.to12HourString(), "12:05 PM")
        time = Time(13, 5)
        assertEquals(time.to12HourString(), "1:05 PM")
        time = Time(23, 5)
        assertEquals(time.to12HourString(), "11:05 PM")
    }
}