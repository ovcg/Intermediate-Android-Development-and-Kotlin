package com.example.notes.ui.tasks

import com.example.notes.foundations.SuccessCallBack
import com.example.notes.models.Task
import com.example.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel {

    fun getData(): MutableList<Task> = mutableListOf(
        Task(
            "Task One", mutableListOf(
                Todo("Test One", true),
                Todo("Test Two")
            )
        ),
        Task("Task Two"),
        Task("Task Three")
    )

    override fun add(model: Task, callback: SuccessCallBack) {
        TODO("Not yet implemented")
    }

    override fun update(model: Task, callback: SuccessCallBack) {
        TODO("Not yet implemented")
    }

    override fun delete(model: Task, callback: SuccessCallBack) {
        TODO("Not yet implemented")
    }

    override fun retrieve(): List<Task> {
      return mutableListOf(
          Task(
              "Task One", mutableListOf(
                  Todo("Test One", true),
                  Todo("Test Two")
              )
          ),
          Task("Task Two"),
          Task("Task Three"))
    }
}