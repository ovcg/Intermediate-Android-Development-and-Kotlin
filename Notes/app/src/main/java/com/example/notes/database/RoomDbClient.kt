package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val dataBaseVersion = 1
const val databaseName = "localDB"

@Database(version = dataBaseVersion, entities = [])
abstract class RoomDBClient : RoomDatabase() {

    companion object {
        private var instance: RoomDBClient? = null

        fun getInstance(ctx: Context): RoomDBClient {
            if (instance == null) {
                createDB(ctx)
            }

            return instance!!
        }

        private fun createDB(ctx: Context): RoomDBClient {
            return Room.databaseBuilder(ctx, RoomDBClient::class.java, databaseName).build()
        }
    }
}