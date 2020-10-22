package com.example.notes.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.R
import com.example.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    private lateinit var task: Task

    fun initView(task: Task, todoCallback: (Int, Boolean) -> Unit) {
        this.task = task

        titleView.text = task.title

        todoContainer.removeAllViews()

        task.todos.forEachIndexed { todoIndex, todo ->

            val todoView = (LayoutInflater.from(context)
                .inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {

                initView(todo) { isChecked ->

                    todoCallback.invoke(todoIndex, isChecked)

                    if (isTaskComplete()) {
                        this@TaskView.titleView.setStrikeThrough()
                    } else {
                        this@TaskView.titleView.removeStrikeThrough()
                    }
                }
            }

            todoContainer.addView(todoView)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.none { !it.isComplete }
}