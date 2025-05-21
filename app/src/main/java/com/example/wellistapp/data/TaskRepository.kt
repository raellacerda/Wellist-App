package com.example.wellistapp.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(task: Task)
    fun getAllTasks() : Flow<List<Task>>
    suspend fun update(task: Task)
    suspend fun delete (task: Task)
    fun getTask(id: Int): Flow<Task>
}