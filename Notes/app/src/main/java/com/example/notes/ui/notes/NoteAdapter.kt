package com.example.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.foundations.BaseRecyclerViewAdapter
import com.example.notes.models.Note
import kotlinx.android.synthetic.main.item_task.view.*

class NoteAdapter(
    noteList: MutableList<Note> = mutableListOf()
) : BaseRecyclerViewAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    class ViewHolder(val view: View) : BaseViewHolder<Note>(view) {

        override fun onBind(model: Note) {
            view.titleView.text = model.description
        }

    }
}