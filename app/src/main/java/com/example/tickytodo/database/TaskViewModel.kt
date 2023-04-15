package com.example.tickytodo.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<Task>>
    val readTrueData: LiveData<List<Task>>
    val readFalseData: LiveData<List<Task>>
    private val repository: TaskRepository
    val isHomeScreenEmpty = MutableLiveData(false)

    val taskDao = TaskDatabase.getDataBase(application).taskDao()
    init {
        val taskDao = TaskDatabase.getDataBase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
        readTrueData = repository.readTrueData
        readFalseData = repository.readFalseData
    }


    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun update(user: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    fun delete(user: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }
    fun updateCheckboxForItem(itemId: Int, checkboxValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCheckboxForItem(itemId, checkboxValue)
        }
    }

}