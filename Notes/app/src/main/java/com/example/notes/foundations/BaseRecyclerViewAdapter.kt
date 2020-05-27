package com.example.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    private val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        const val typeAddButton = 0
        const val typeInfo = 1

    }

    fun updateList(list: MutableList<T>) {
        //val result = DiffUtil.calculateDiff(DiffUtilCallBackImpl(masterList, list))
        masterList.clear()
        masterList.addAll(list)
        notifyDataSetChanged()
        //result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        typeAddButton
    } else {
        typeInfo
    }

    override fun getItemCount(): Int = masterList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit, position - 1)
        } else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1], position - 1)
        }
    }

    abstract class BaseViewHolder<T>(private val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(model: T, listIndex: Int)
    }

    abstract class AddButtonViewHolder(view: View) : BaseViewHolder<Unit>(view)

    /*class DiffUtilCallBackImpl<T>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback(){

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
             oldList[oldItemPosition] == newList[newItemPosition]
    }*/
}