package com.example.stroryapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stroryapp.data.StoryRepository
import java.io.File

class AddViewModel(private val repository: StoryRepository) : ViewModel() {
    fun getSession() = repository.getSession().asLiveData()
    fun uploadImage(token: String, file: File, description: String, lat: String, lon: String) = repository.uploadImage(token, file, description, lat, lon)
    fun uploadImage(token: String, file: File, description: String) = repository.uploadImage(token, file, description)
}