package com.example.stroryapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stroryapp.Respon.StoriesResponse
import com.example.stroryapp.data.StoryRepository
import kotlinx.coroutines.launch
import com.example.stroryapp.data.Result

class MapViewModel(private val repository: StoryRepository) : ViewModel() {
    fun getSession() = repository.getSession().asLiveData()

    private val _location = MutableLiveData<Result<StoriesResponse>>()
    val location: LiveData<Result<StoriesResponse>> = _location

    fun getStoriesWithLocation(token: String) {
        viewModelScope.launch {
            repository.getStoriesWithLocation(token).asFlow().collect{
                _location.value = it
            }
        }

    }

}