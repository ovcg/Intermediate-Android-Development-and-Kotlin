package com.example.notes.ui.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1) : ConstraintLayout ( ctx, attrs, defStyleAttr){

    fun initView(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        descriptionView?.text = todo.description
        completeCheckBox?.isChecked = todo.isComplete

        if (todo.isComplete) {
            createStrikeThrough()
        }

        setUpCheckStateListener(callback)
    }

    fun setUpCheckStateListener(callback: ((Boolean) -> Unit)? = null) {
        completeCheckBox?.let { cb ->
            cb.setOnCheckedChangeListener { _, isChecked ->
                callback?.invoke(isChecked)
                if (isChecked) {
                    createStrikeThrough()
                } else {
                    removeStrikeThrough()
                }
            }
        }
    }

    private fun createStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

}