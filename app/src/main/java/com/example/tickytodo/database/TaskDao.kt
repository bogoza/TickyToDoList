package com.example.tickytodo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task_table")
    fun readAllData(): LiveData<List<Task>>

    @Update(entity = Task::class)
    fun update(task: Task)


    @Delete(entity = Task::class)
    fun delete(task: Task)
}