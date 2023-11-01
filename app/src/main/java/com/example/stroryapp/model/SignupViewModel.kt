package com.example.stroryapp.model


import androidx.lifecycle.ViewModel
import com.example.stroryapp.data.StoryRepository

class SignupViewModel(private val repository: StoryRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) = repository.signup(name, email, password)
}