package com.example.todo.data.repository

import com.example.todo.data.ChoresDao
import com.example.todo.data.models.ChoresData

/**
 * An abstraction class on top of data layer for both remote & local
 * */
class ChoresRepository(private val choresDao: ChoresDao) {

    fun getAllChoresData() = choresDao.getAllChoresData()

    suspend fun insertChore(chore: ChoresData) = choresDao.insertChore(chore)
}