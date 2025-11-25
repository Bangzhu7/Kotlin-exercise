package com.example.timetracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val totalSeconds: Long = 0,
    val isRunning: Boolean = false,
    val startTime: Long = 0,
    val createdAt: Long = System.currentTimeMillis()
)