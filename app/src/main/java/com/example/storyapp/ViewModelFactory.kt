package com.example.storyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.data.local.UserPreference
import com.example.storyapp.di.Injection
import com.example.storyapp.ui.detail.DetailViewModel
import com.example.storyapp.ui.login.LoginViewModel
import com.example.storyapp.ui.main.MainViewModel
import com.example.storyapp.ui.maps.MapsViewModel
import com.example.storyapp.ui.upload.UploadViewModel

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java)-> {
                LoginViewModel(pref) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(pref) as T
            }
            modelClass.isAssignableFrom(UploadViewModel::class.java)->{
                UploadViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java)->{
                MapsViewModel(pref) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}