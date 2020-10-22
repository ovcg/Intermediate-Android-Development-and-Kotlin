package com.example.notes.ui.tasks

import com.example.notes.application.NoteApplication
import com.example.notes.database.RoomDBClient
import com.example.notes.foundations.SuccessCallback
import com.example.notes.models.Task
import com.example.notes.models.Todo
import com.example.notes.utils.ConstantsUtil
import com.example.notes.utils.ConstantsUtil.TIMEOUT_DURATION_MILLIS
import kotlinx.coroutines.*
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel {

    private var databaseClient =
        RoomDBClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(
        function: () -> Unit,
        callback: SuccessCallback
    ) {

        val job = GlobalScope.async {
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: java.lang.Exception) {
                callback.invoke(false)
            }
        }
        job.await()
        callback.invoke(true)

    }

    override suspend fun add(model: Task, callback: SuccessCallback) {

        val masterJob = GlobalScope.async {
            // adds task entity component
            try {
                databaseClient?.taskDao()?.addTask(model)
            } catch (e: Exception) {
                callback.invoke(false)
            }
            // adds todos list component
            addTodosJob(model)
        }
        masterJob.await()
        callback.invoke(true)
    }

    override suspend fun update(model: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient?.taskDao()?.updateTask(model) }, callback)
    }

    override suspend fun updateTodo(todo: Todo, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient?.taskDao()?.updateTodo(todo) }, callback)
    }

    override suspend fun delete(model: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient?.taskDao()?.deleteTask(model) }, callback)
    }

    override fun retrieve(callback: (List<Task>?) -> Unit) {
        GlobalScope.launch {
            val job = async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                    databaseClient?.taskDao()?.retrieveTasks()
                }
            }
            callback.invoke(job.await())
        }
    }

    private fun addTodosJob(task: Task): Job = GlobalScope.async {
        task.todos.forEach {
            databaseClient?.taskDao()?.addTodo(it)
        }
    }
}