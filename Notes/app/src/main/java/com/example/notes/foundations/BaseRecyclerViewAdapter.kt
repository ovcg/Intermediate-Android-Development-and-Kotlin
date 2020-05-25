package com.example.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.ui.tasks.TasksAdapter

abstract class BaseRecyclerViewAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        const val typeAddButton = 0
        const val typeInfo = 1

    }

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        typeAddButton
    } else {
        typeInfo
    }

    override fun getItemCount(): Int = masterList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit)
        } else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1])
        }
    }

    abstract class BaseViewHolder<T>(private val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(model: T)
    }

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)
}