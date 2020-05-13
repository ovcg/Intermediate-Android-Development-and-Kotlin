package com.example.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = masterList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      (holder as BaseViewHolder<T>).onBind(masterList[position])
    }

    abstract class BaseViewHolder<T>(private val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(model: T)
    }
}