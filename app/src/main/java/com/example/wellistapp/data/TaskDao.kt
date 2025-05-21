package com.example.wellistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks ORDER BY createdIn ASC")
    fun getAllTasks() : Flow<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete (task: Task)

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Task>
}