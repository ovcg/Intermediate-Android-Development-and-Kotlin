package com.example.notes.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.foundations.BaseFragment
import com.example.notes.foundations.BaseRecyclerViewAdapter
import com.example.notes.models.Task
import com.example.notes.ui.navigation.NavigationActivity
import com.example.notes.ui.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*

class TasksAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    private val touchActionDelegate: BaseFragment.TouchActionDelegate
) : BaseRecyclerViewAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == typeInfo) {
            TaskViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            )
        } else {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_add_button, parent, false)
            )
        }

    class TaskViewHolder(private val view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(model: Task) {
            (view as TaskView).initView(model)
        }
    }

    inner class AddButtonViewHolder(private val view: View): BaseRecyclerViewAdapter.AddButtonViewHolder(view) {
        override fun onBind(model: Unit) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)

            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(NavigationActivity.fragmentValueTask)
            }
        }
    }

}