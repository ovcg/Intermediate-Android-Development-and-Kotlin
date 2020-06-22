package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.foundations.ApplicationScope
import com.example.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel : ViewModel(), NotesListViewContract {

    @Inject
    lateinit var noteModel: INoteModel

    private val _notesListLiveData = MutableLiveData<MutableList<Note>>()
    val notesListLiveData: LiveData<MutableList<Note>> = _notesListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        _notesListLiveData.postValue(noteModel.retrieve().toMutableList())

    }
}