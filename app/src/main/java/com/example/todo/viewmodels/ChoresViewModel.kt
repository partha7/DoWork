package com.example.todo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.data.ChoresDatabase
import com.example.todo.data.models.ChoresData
import com.example.todo.data.models.Priority
import com.example.todo.data.repository.ChoresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel is use to connect data layer with UI layer, most useful as it survives configuration changes
 * */
class ChoresViewModel(application: Application): AndroidViewModel(application) {

    private val database = ChoresDatabase.getChoresDatabaseInstance(application)
    private val choresDao = database.getChoresDao()

    private val choresRepository: ChoresRepository = ChoresRepository(choresDao)

    val getAllChoresData: LiveData<List<ChoresData>> = choresRepository.getAllChoresData()

    fun insertChore(chore: ChoresData) {
        viewModelScope.launch(Dispatchers.IO) {
            choresRepository.insertChore(chore)
        }
    }

}