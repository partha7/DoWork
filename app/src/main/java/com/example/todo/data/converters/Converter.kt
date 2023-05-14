package com.example.todo.data.converters

import com.example.todo.data.models.Priority

class Converter {

    @androidx.room.TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @androidx.room.TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}