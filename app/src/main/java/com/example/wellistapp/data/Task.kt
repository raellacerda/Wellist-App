package com.example.wellistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class Task (
    @PrimaryKey(autoGenerate = true) val id : Int,
    val title : String,
    val description: String,
    val priority: Priority,
    val createdIn : Date,
    val term : Date?
)

