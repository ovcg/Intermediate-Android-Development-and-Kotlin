package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.models.Note
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class NotesViewModel : ViewModel(), NotesListViewContract {

    @Inject
    lateinit var noteModel: INoteModel

    private val _notesListLiveData = MutableLiveData<MutableList<Note>>()
    val notesListLiveData: LiveData<MutableList<Note>> = _notesListLiveData

    init {
        val scope = Toothpick.openScope(this)
        scope.installModules(
            Module().apply {
                bind(INoteModel::class.java).toInstance(NoteLocalModel())
            })
        Toothpick.inject(this, scope)
        _notesListLiveData.postValue(noteModel.retrieve().toMutableList())

    }
}