package com.example.storyapp.ui.main

import androidx.lifecycle.*
import androidx.paging.*
import com.example.storyapp.data.api.ListStoryItem
import com.example.storyapp.data.StoryRepository
import com.example.storyapp.data.local.Story

class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    val stories : LiveData<PagingData<Story>> =
        storyRepository.getAllStories().cachedIn(viewModelScope)

    fun getAllStories() = storyRepository.getAllStoriesNotPaging()

}