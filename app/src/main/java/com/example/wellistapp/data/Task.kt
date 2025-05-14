package com.example.wellistapp.data

import java.util.Date


data class Task (
    val id : Int,
    val title : String,
    val description: String,
    val priority: Priority,
    val createdIn : Date,
    val term : Date?
)

