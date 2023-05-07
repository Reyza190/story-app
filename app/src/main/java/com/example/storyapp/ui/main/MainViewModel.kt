package com.example.storyapp.ui.main

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.*
import com.example.storyapp.data.StoryPagingSource
import com.example.storyapp.data.api.ApiConfig
import com.example.storyapp.data.api.ListStoryItem
import com.example.storyapp.data.api.StoryRepository
import com.example.storyapp.data.local.UserPreference
import com.example.storyapp.di.Injection

class MainViewModel(storyRepository: StoryRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val client = ApiConfig.getApiService()

    val stories : LiveData<PagingData<ListStoryItem>> =
        storyRepository.getAllStories().cachedIn(viewModelScope)

//    fun getAllStories(token: String): LiveData<PagingData<ListStoryItem>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            pagingSourceFactory = {
//                StoryPagingSource(client, token)
//            }
//        ).liveData
//
//    }

//    fun getStories(token: String): LiveData<PagingData<ListStoryItem>>{
//        return storyRepository.getAllStories(token).cachedIn(viewModelScope)
//    }


//    suspend fun clearData() {
//        return pref.deleteSession()
//    }

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(Injection.provideRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}