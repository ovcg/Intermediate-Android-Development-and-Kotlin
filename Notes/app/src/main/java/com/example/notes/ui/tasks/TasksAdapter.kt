package com.example.notes.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.models.Tasks
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(
    private val taskList: MutableList<Tasks> = mutableListOf()
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(task: Tasks){
            view.titleView.text = task.title
        }
    }
}