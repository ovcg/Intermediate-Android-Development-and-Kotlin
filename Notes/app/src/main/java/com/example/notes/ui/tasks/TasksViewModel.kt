package com.example.notes.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.models.Task
import com.example.notes.models.Todo

class TasksViewModel : ViewModel(), TaskListViewContract {

    private val _taskListLiveData = MutableLiveData<MutableList<Task>>()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {

        _taskListLiveData.postValue(getData())

    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

    private fun getData(): MutableList<Task> = mutableListOf(
        Task("Task One", mutableListOf(
            Todo("Test One", true),
            Todo("Test Two")
        )),
        Task("Task Two"),
        Task("Task Three")
    )

}