
// File: com/example/flowtime/data/Category.kt
package com.example.flowtime.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val colorHex: String = "#CCCCCC" // 存储颜色代码，用于 Material 3 设计
)