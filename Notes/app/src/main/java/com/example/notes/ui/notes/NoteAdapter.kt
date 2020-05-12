package com.example.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.models.Note
import com.example.notes.models.Tasks
import kotlinx.android.synthetic.main.item_task.view.*

class NoteAdapter(
    private val noteList: MutableList<Note> = mutableListOf()
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(noteList[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(note: Note){
            view.titleView.text = note.description
        }
    }
}