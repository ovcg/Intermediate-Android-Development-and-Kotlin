package com.example.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.foundations.BaseFragment
import com.example.notes.foundations.BaseRecyclerViewAdapter
import com.example.notes.models.Note
import com.example.notes.ui.navigation.NavigationActivity
import com.example.notes.ui.views.NoteView
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_add_button.view.*

class NotesAdapter(
    noteList: MutableList<Note> = mutableListOf(),
    private val touchActionDelegate: BaseFragment.TouchActionDelegate
) : BaseRecyclerViewAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == typeInfo) {
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
            )
        }else{
            AddButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false)
            )
        }


    class ViewHolder(private val view: View) : BaseViewHolder<Note>(view) {

        override fun onBind(model: Note) {
            (view as NoteView).apply {
                initView(model)
            }
        }
    }

    inner class AddButtonViewHolder(private val view: View) :
        BaseRecyclerViewAdapter.AddButtonViewHolder(view) {
        override fun onBind(model: Unit) {
            view.buttonText.text = view.context.getString(R.string.add_button_note)

            view.setOnClickListener{
                touchActionDelegate.onAddButtonClicked(NavigationActivity.fragmentValueNote)
            }
        }
    }
}