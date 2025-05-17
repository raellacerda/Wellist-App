package com.example.wellistapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks ORDER BY createdIn ASC")
    fun getAllTasks() : Flow<List<Task>>
}