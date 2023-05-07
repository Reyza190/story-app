package com.example.storyapp.ui.login

import androidx.lifecycle.*
import com.example.storyapp.data.api.ApiConfig
import com.example.storyapp.data.api.LoginResponse
import com.example.storyapp.data.local.UserPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val client = ApiConfig.getApiService()

    fun login(email: String, password: String) {
        _loading.value = true
        client.login(email, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _loading.value = false
                if (response.isSuccessful) {
                    _message.value = response.body()?.message
                    viewModelScope.launch {
                        pref.saveKey(response.body()?.loginResult?.token.toString())
                    }
                }else{
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loading.value = false
                _message.value = t.message
            }

        })
    }

    fun stateData(): LiveData<String> {
        return pref.getKey().asLiveData()
    }
}