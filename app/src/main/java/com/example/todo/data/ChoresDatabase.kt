package com.example.todo.data

import android.content.Context
import androidx.room.*
import com.example.todo.data.converters.Converter
import com.example.todo.data.models.ChoresData

@Database(entities = [ChoresData::class], version = 1, exportSchema = true)
@TypeConverters(Converter::class)
abstract class ChoresDatabase: RoomDatabase() {

    abstract fun getChoresDao(): ChoresDao

    companion object {
        @Volatile
        private var INSTANCE: ChoresDatabase? = null

        fun getChoresDatabaseInstance(context: Context): ChoresDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null )
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    ChoresDatabase::class.java,
                    "chores_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}