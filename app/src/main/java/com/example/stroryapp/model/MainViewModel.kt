package com.example.stroryapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.stroryapp.Respon.ListStoryItem
import com.example.stroryapp.data.StoryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: StoryRepository) : ViewModel() {
    fun getSession() = repository.getSession().asLiveData()
    fun getStory (token: String) : LiveData<PagingData<ListStoryItem>> = repository.getPaging(token)
    fun getLogout () {
        viewModelScope.launch {
            repository.logout()
        }
    }
}