// File: com/example/flowtime/data/TimeEntry.kt (使用 Long 时间戳简化)
package com.example.flowtime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_entries")
data class TimeEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val categoryId: Long, // 关联到 Category.id
    val taskDescription: String,
    val startTimeMillis: Long, // 使用 Long 存储时间戳 (System.currentTimeMillis())
    val endTimeMillis: Long?, // 结束时间，可为空
    val durationSeconds: Long = 0 // 总时长（以秒为单位）
)