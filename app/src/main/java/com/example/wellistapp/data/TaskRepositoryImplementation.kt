package com.example.wellistapp.data

import kotlinx.coroutines.flow.Flow

class TaskRepositoryImplementation (private val taskDao: TaskDao) : TaskRepository {
    override suspend fun insert(task: Task) = taskDao.insert(task)
    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    override suspend fun update(task: Task) = taskDao.update(task)
    override suspend fun delete(task: Task) = taskDao.delete(task)
    override fun getTask(id: Int): Flow<Task> = taskDao.getTask(id)
}