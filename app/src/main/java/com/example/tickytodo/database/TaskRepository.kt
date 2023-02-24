package com.example.tickytodo.database

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {
    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
    fun update(task: Task) {
        taskDao.update(task)
    }
    fun delete(task:Task){
        taskDao.delete(task)
    }
}
