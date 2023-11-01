package com.example.stroryapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stroryapp.data.StoryRepository

class DetailViewModel(private val repository: StoryRepository) : ViewModel() {
    fun getSession() = repository.getSession().asLiveData()
    fun getDetail(token: String, id: String) = repository.getDetail(token,id)
}