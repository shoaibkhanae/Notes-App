package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.local.NotesDao
import com.example.notesapp.data.local.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDao(database: NotesDatabase): NotesDao = database.getDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "notes_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}