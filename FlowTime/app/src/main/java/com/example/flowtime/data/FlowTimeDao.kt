// File: com/example/flowtime/data/FlowTimeDao.kt
package com.example.flowtime.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowTimeDao {

    // --- Category DAO ---

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<Category>> // 使用 Flow 观察数据变化

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    // --- TimeEntry DAO ---

    @Query("SELECT * FROM time_entries ORDER BY startTimeMillis DESC")
    fun getAllTimeEntries(): Flow<List<TimeEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeEntry(entry: TimeEntry)

    @Update
    suspend fun updateTimeEntry(entry: TimeEntry)

    @Delete
    suspend fun deleteTimeEntry(entry: TimeEntry)
}