package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.models.Note
import com.example.notes.models.Tag
import com.example.notes.models.TaskEntity
import com.example.notes.models.Todo

const val dataBaseVersion = 1
const val databaseName = "localDB"

@Database(version = dataBaseVersion, entities = [TaskEntity::class, Note::class, Tag::class, Todo::class])
abstract class RoomDBClient : RoomDatabase() {

    abstract fun noteDao(): NoteDAO

    abstract fun taskDao(): TaskDAO

    companion object {

        private var instance: RoomDBClient? = null

        fun getInstance(ctx: Context): RoomDBClient? {

                if (instance == null) {
                    createDB(ctx)
                }

            return instance
        }

        private fun createDB(ctx: Context): RoomDBClient {
            return Room.databaseBuilder(ctx, RoomDBClient::class.java, databaseName)
                .allowMainThreadQueries()
                .build()
        }

    }
}