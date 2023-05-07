package com.example.storyapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.storyapp.data.api.ApiConfig
import com.example.storyapp.data.api.DetailStoriesResponse
import com.example.storyapp.data.api.LoginResult
import com.example.storyapp.data.api.Story
import com.example.storyapp.data.local.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val pref: UserPreference) : ViewModel() {

    private val _story = MutableLiveData<Story>()
    val story: LiveData<Story> = _story

    private val client = ApiConfig.getApiService()

    fun getDetailStory(token: String, id: String){
        client.detailStory("Bearer $token", id).enqueue(object : Callback<DetailStoriesResponse> {
            override fun onResponse(
                call: Call<DetailStoriesResponse>,
                response: Response<DetailStoriesResponse>
            ) {
                if (response.isSuccessful){
                    _story.value = response.body()?.story
                }
            }

            override fun onFailure(call: Call<DetailStoriesResponse>, t: Throwable) {
                Log.d("detail", t.message.toString())
            }

        })
    }
}