// File: com/example/flowtime/data/FlowTimeDatabase.kt
package com.example.flowtime.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Category::class, TimeEntry::class], version = 1, exportSchema = false)
abstract class FlowTimeDatabase : RoomDatabase() {

    abstract fun flowTimeDao(): FlowTimeDao

    // 稍后将在这里添加伴生对象 (Companion Object) 和单例 (Singleton) 实现
}