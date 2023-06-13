package com.example.storyapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import com.example.storyapp.data.api.*
import com.example.storyapp.data.local.StoryDatabase
import okhttp3.MultipartBody
import okhttp3.RequestBody


class StoryRepository(
    private val apiService: ApiService,
    pref: UserPreference,
    private val database: StoryDatabase
) {

    private val token = pref.getToken()

    fun getAllStories(): LiveData<PagingData<com.example.storyapp.data.local.Story>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(database, apiService, token.toString()),
            pagingSourceFactory = {
                database.storyDao().getAllStory()
            }
        ).liveData
    }

    fun getAllStoriesNotPaging(): LiveData<Result<StoriesResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllStories("Bearer $token")
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("MainViewModel", "Main: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "Login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<GeneralResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "Register: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun uploadStory(
        file: MultipartBody.Part,
        description: RequestBody
    ): LiveData<Result<GeneralResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.uploadStory("Bearer $token", file, description)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("UploadViewModel", "uploadStory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAllStoriesWithLocation(
        location: Int
    ): LiveData<Result<StoriesResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllStoriesWithLocation("Bearer $token", location)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("MapsViewModel", "Maps: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun uploadStoryWithLocation(
        file: MultipartBody.Part,
        description: RequestBody,
        lat: Float,
        lon: Float
    ): LiveData<Result<GeneralResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response =
                apiService.uploadStoryWithLocation("Bearer $token", file, description, lat, lon)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("UploadViewModel", "uploadStory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

}