package com.example.notesapp.data.repository

import com.example.notesapp.data.local.Item
import com.example.notesapp.data.local.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainRepository @Inject constructor(private val notesDao: NotesDao) {

    val allNotes: Flow<List<Item>> = notesDao.getAllNotes()

    suspend fun insert(item: Item) {
        notesDao.insert(item)
    }

    suspend fun update(item: Item) {
        notesDao.update(item)
    }

    suspend fun delete(item: Item) {
        notesDao.delete(item)
    }
}