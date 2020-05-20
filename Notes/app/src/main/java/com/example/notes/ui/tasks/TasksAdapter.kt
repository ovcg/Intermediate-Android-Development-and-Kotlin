package com.example.notes.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.foundations.BaseRecyclerViewAdapter
import com.example.notes.models.Task
import com.example.notes.ui.views.TaskView

class TasksAdapter(
    taskList: MutableList<Task> = mutableListOf()
) : BaseRecyclerViewAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    class ViewHolder(private val view: View) : BaseViewHolder<Task>(view) {

        override fun onBind(model: Task) {
            (view as TaskView).apply {
                initView(model)
            }
        }
    }
}