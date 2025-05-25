package com.example.wellistapp.di

import android.content.Context
import androidx.room.Room
import com.example.wellistapp.data.AppDatabase
import com.example.wellistapp.data.TaskDao
import com.example.wellistapp.data.TaskRepository
import com.example.wellistapp.data.TaskRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton  // banco de dados singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton  // DAO singleton, porque é criado a partir do DB singleton
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()

    @Provides
    @Singleton  // repositório singleton para injetar na ViewModel
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepositoryImplementation(dao)
}