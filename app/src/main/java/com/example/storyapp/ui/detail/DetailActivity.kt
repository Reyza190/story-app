package com.example.storyapp.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.storyapp.ViewModelFactory
import com.example.storyapp.data.api.ListStoryItem
import com.example.storyapp.data.local.UserPreference
import com.example.storyapp.databinding.ActivityDetailBinding
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[DetailViewModel::class.java]

        val story = intent.getParcelableExtra<ListStoryItem>("STORY")
        binding.apply {
            Glide.with(this@DetailActivity).load(story?.photoUrl).into(ivStory)
            tvUserName.text = story?.name
            tvDescription.text = story?.description
        }




    }
}