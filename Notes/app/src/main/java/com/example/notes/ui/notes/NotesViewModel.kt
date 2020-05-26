package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.models.Note

class NotesViewModel : ViewModel(), NotesListViewContract {

    private val _notesListLiveData = MutableLiveData<MutableList<Note>>()
    val notesListLiveData: LiveData<MutableList<Note>> = _notesListLiveData

    init {

        _notesListLiveData.postValue(getData())

    }

    private fun getData(): MutableList<Note> =   mutableListOf(
        Note("Note 1"),
        Note("Note 2"),
        Note("Note 3"),
        Note("Note 4")
    )


}