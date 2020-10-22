package com.example.notes.ui.notes

import com.example.notes.application.NoteApplication
import com.example.notes.database.RoomDBClient
import com.example.notes.foundations.SuccessCallback
import com.example.notes.models.Note
import com.example.notes.utils.ConstantsUtil.TIMEOUT_DURATION_MILLIS
import kotlinx.coroutines.*
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDBClient? =
        RoomDBClient.getInstance(NoteApplication.instance.applicationContext)

    override suspend fun add(model: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient?.noteDao()?.addNote(model) }, callback)
    }

    override suspend fun update(model: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient?.noteDao()?.updateNote(model) }, callback)
    }

    override suspend fun delete(model: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient?.noteDao()?.deleteNote(model) }, callback)
    }

    override fun retrieve(callback: (List<Note>?) -> Unit) {
        GlobalScope.launch {
            val job = GlobalScope.async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                    databaseClient?.noteDao()?.retrieveNotes()
                }
            }
            callback.invoke(job.await())
        }
    }

    private suspend fun performOperationWithTimeout(
        function: () -> Unit,
        callback: SuccessCallback
    ) {
        val job = GlobalScope.async {
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: java.lang.Exception) {
                callback.invoke(false)
            }
        }
        job.await()
        callback.invoke(true)
    }

}