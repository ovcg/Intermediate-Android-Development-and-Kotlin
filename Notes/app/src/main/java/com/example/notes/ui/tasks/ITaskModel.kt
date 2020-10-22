package com.example.notes.ui.tasks

import com.example.notes.foundations.IModel
import com.example.notes.foundations.SuccessCallback
import com.example.notes.models.Task
import com.example.notes.models.Todo

interface ITaskModel : IModel<Task> {
    suspend fun updateTodo(todo: Todo, callback: SuccessCallback)
}