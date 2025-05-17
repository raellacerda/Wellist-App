package com.example.wellistapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun taskDao() : TaskDao


    //singleton
    companion object {
        @Volatile
        private var Instance : AppDatabase? = null

        fun getDatabase (context: Context) : AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = "app_database"
                ).build()
                    .also { Instance = it }
            }
        }

    }

}