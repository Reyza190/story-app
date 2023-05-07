package com.example.storyapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.storyapp.data.api.ApiConfig
import com.example.storyapp.data.api.StoryRepository
import com.example.storyapp.data.local.UserPreference
object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val pref = com.example.storyapp.data.UserPreference(context)
        return StoryRepository(apiService, pref)
    }
}