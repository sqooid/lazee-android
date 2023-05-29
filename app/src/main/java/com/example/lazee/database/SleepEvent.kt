package com.example.lazee.database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.util.Date

@Entity
data class SleepEvent(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "start_time") val startTime: Date,
    @ColumnInfo(name = "end_time") val endTime: Date,
)

@Dao
interface SleepEventDao {
    @Query("Select * from SleepEvent")
    fun getAll(): List<SleepEvent>

    @Insert
    fun insert(event:SleepEvent)

}
