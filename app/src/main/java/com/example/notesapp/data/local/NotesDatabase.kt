package com.example.notesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getDao(): NotesDao

}