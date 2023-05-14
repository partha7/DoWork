package com.example.todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chores_data")
data class ChoresData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
)
