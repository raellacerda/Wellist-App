package com.example.wellistapp.data

import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter



class Converters {

    @TypeConverter
    fun priorityToInt(priority: Priority) : Int {
        val converted = when(priority) {
            Priority.LOW -> 0
            Priority.MEDIUM -> 1
            Priority.HIGH -> 2
        }
        return converted
    }
    @TypeConverter
    fun intToPriority(value: Int): Priority {
        return when(value) {
            0 -> Priority.LOW
            1 -> Priority.MEDIUM
            2 -> Priority.HIGH
            else -> Priority.LOW // ou jogar exceção
        }
    }


    fun timestampToString (date: Long?) : String {
        return if (date != null) {
            val date = Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = date.format(formatter)
            formatted
        } else {
            "could not convert"
        }
    }




}