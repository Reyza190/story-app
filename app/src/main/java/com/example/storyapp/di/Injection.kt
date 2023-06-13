package com.example.storyapp.di

import android.content.Context
import com.example.storyapp.data.api.ApiConfig
import com.example.storyapp.data.StoryRepository
import com.example.storyapp.data.local.StoryDatabase

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val pref = com.example.storyapp.data.UserPreference(context)
        val database = StoryDatabase.getInstance(context)
        return StoryRepository(apiService, pref, database)
    }
}