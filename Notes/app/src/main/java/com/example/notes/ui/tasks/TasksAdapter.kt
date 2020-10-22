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
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_add_button.view.*

class TasksAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    private val touchActionDelegate: BaseFragment.TouchActionDelegate,
    private val dataActionDelegate: TaskListViewContract
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

    inner class TaskViewHolder(private val view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(model: Task, listIndex: Int) {
            (view.taskView as TaskView).initView(model) { todoIndex, isChecked ->

                dataActionDelegate.onTodoUpdated(listIndex, todoIndex, isChecked)
            }
        }
    }

    inner class AddButtonViewHolder(private val view: View) :
        BaseRecyclerViewAdapter.AddButtonViewHolder(view) {
        override fun onBind(model: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)

            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(NavigationActivity.fragmentValueTask)
            }
        }
    }

}