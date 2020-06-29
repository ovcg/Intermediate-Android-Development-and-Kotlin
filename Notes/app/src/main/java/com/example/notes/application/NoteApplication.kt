package com.example.notes.application

import android.app.Application

class NoteApplication : Application() {

    companion object{
        lateinit var instance: NoteApplication
    }

    init {
        instance = this
    }
}