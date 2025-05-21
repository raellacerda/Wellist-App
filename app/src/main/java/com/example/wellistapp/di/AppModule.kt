package com.example.wellistapp.di

import com.example.wellistapp.data.AppDatabase
import com.example.wellistapp.data.TaskDao
import com.example.wellistapp.data.TaskRepository
import com.example.wellistapp.data.TaskRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository {
        return TaskRepositoryImplementation(dao)
    }

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()

}