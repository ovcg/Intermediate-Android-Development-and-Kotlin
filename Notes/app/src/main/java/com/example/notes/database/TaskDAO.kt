package com.example.notes.database

import androidx.room.*
import com.example.notes.models.Task
import com.example.notes.models.TaskEntity
import com.example.notes.models.Todo

@Dao
interface TaskDAO {

    @Insert
    fun addTask(task: TaskEntity)

    @Update
    fun updateTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks(): MutableList<Task>

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

}
