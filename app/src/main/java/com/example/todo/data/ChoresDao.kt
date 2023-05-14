package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo.data.models.ChoresData

@Dao
interface ChoresDao {

    @Query("SELECT * FROM chores_data ORDER BY id ASC")
    fun getAllChoresData(): LiveData<List<ChoresData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChore(choresData: ChoresData)
}