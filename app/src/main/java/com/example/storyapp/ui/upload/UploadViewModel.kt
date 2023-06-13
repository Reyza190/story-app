package com.example.storyapp.ui.upload

import androidx.lifecycle.ViewModel
import com.example.storyapp.data.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun uploadStory(file: MultipartBody.Part, description: RequestBody) =
        storyRepository.uploadStory(file, description)

    fun uploadStoryWithLocation(
        file: MultipartBody.Part,
        description: RequestBody,
        lat: Float,
        lon: Float
    ) = storyRepository.uploadStoryWithLocation(file, description, lat, lon)
}