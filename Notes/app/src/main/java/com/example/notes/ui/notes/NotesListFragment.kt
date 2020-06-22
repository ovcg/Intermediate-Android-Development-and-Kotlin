package com.example.notes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.foundations.BaseFragment

class NotesListFragment : BaseFragment() {

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var contentView: NotesListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false).apply {
            contentView = this as NotesListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setContentView()

    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate, notesViewModel)
    }

    private fun bindViewModel() {
        notesViewModel =
            ViewModelProvider(this).get(NoteViewModel::class.java)
        notesViewModel.notesListLiveData.observe(viewLifecycleOwner, Observer {
            contentView.updateList(it)
        })
    }
}
