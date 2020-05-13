package com.example.notes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes.*

class NoteListFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel =
            ViewModelProviders.of(this).get(NotesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mAdapter = NoteAdapter(
            mutableListOf(
                Note("Note 1"),
                Note("Note 2"),
                Note("Note 3"),
                Note("Note 4")
            )
        )
        with(recyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }
}
