package com.example.notesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.Item
import com.example.notesapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val allNotes = repository.allNotes.asLiveData()

    fun insert(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(item)
        }
    }

    fun update(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item)
        }
    }

    fun delete(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }
}