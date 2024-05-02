package com.example.notesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
)
