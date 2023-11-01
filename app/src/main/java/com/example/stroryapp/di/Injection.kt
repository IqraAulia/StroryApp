package com.example.stroryapp.di

import android.content.Context
import com.example.stroryapp.data.StoryRepository
import com.example.stroryapp.data.pref.UserPreference
import com.example.stroryapp.data.pref.dataStore
import com.example.stroryapp.retrofit.ApiConfig



object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val pref = UserPreference.getInstance(context.dataStore)
        return StoryRepository.getInstance(apiService,pref)
    }
}