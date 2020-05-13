package com.example.notes.ui.tasks

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import com.example.notes.foundations.BaseRecyclerViewAdapter
import com.example.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_todo.view.*

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
            view.titleView.text = model.title
            model.todos.forEach { todo ->

                val todoView = LayoutInflater.from(view.context)
                    .inflate(R.layout.view_todo, view.todoContainer, false).apply {
                        descriptionView.text = todo.description

                        completeCheckBox.isChecked = todo.isComplete

                        completeCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                            descriptionView.paintFlags =  if (isChecked){
                                descriptionView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                            }else{
                                descriptionView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                            }
                        }
                    }
                view.todoContainer.addView(todoView)

            }
        }
    }
}