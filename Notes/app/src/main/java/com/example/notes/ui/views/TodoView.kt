package com.example.notes.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    fun initView(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        descriptionView?.text = todo.description
        completeCheckBox?.isChecked = todo.isComplete

        if (todo.isComplete) {
            descriptionView.setStrikeThrough()
        }

        setUpCheckStateListener(todo, callback)
    }

    private fun setUpCheckStateListener(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        completeCheckBox?.let { cb ->
            cb.setOnCheckedChangeListener { _, isChecked ->

                todo.isComplete = isChecked

                callback?.invoke(isChecked)

                if (isChecked) {
                    descriptionView.setStrikeThrough()
                } else {
                    descriptionView.removeStrikeThrough()
                }
            }
        }
    }
}