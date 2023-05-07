package com.example.storyapp.ui.main

import androidx.lifecycle.*
import androidx.paging.*
import com.example.storyapp.data.StoryPagingSource
import com.example.storyapp.data.api.ApiConfig
import com.example.storyapp.data.api.ListStoryItem
import com.example.storyapp.data.local.UserPreference

class MainViewModel(private val pref: UserPreference) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    private val client = ApiConfig.getApiService()

    fun getAllStories(token: String): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(client, token)
            }
        ).liveData

    }

    suspend fun clearData() {
        return pref.deleteSession()
    }
}