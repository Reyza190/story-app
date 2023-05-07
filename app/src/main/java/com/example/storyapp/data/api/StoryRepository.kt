package com.example.storyapp.data.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.storyapp.data.StoryPagingSource
import com.example.storyapp.data.local.UserPreference


class StoryRepository(private val apiService: ApiService, private val pref: com.example.storyapp.data.UserPreference) {

    val token = pref.getToken()

    fun getAllStories(): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService, token.toString())
            }
        ).liveData
    }

}