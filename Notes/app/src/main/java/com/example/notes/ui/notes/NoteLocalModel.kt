package com.example.notes.ui.notes

import com.example.notes.application.NoteApplication
import com.example.notes.database.RoomDBClient
import com.example.notes.foundations.SuccessCallBack
import com.example.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient = RoomDBClient.getInstance(NoteApplication.instance.applicationContext)

    override fun add(model: Note, callback: SuccessCallBack) {
        TODO("Not yet implemented")
    }

    override fun update(model: Note, callback: SuccessCallBack) {
        TODO("Not yet implemented")
    }

    override fun delete(model: Note, callback: SuccessCallBack) {
        TODO("Not yet implemented")
    }

    override fun retrieve(): List<Note> {
        return mutableListOf(
            Note("Note 1"),
            Note("Note 2"),
            Note("Note 3"),
            Note("Note 4")
        )
    }
}