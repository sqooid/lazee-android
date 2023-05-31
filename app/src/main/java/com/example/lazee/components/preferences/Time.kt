package com.example.lazee.components.preferences


fun timeFromString(timeString: String): Time {
    try {

        val timeValues = timeString.split(":").map { it.toInt() }
        return Time(timeValues[0], timeValues[1])
    } catch (error: Exception) {
        throw Exception("Failed to parse time string '$timeString'")
    }
}

class Time(val hour: Int, val minute: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Time) {
            return other.hour == this.hour && other.minute == this.minute
        }
        return false
    }

    fun to12HourString(): String {
        return "${
            if (hour % 12 == 0) {
                12
            } else {
                hour % 12
            }
        }:${minute.toString().padStart(2, '0')} ${
            if (hour < 12) {
                "AM"
            } else {
                "PM"
            }
        }"
    }

    override fun toString(): String {
        return "${hour}:${minute}"
    }

    override fun hashCode(): Int {
        return hour * 60 + minute
    }
}