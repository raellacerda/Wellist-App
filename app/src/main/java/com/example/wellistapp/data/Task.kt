package com.example.wellistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val title : String,
    val description: String,
    val priority: Priority,
    val createdIn : Long = Date().time,
    val term : Long,
    val isDone: Boolean = false
)

