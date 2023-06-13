package com.example.storyapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapp.databinding.ListStoryBinding
import com.example.storyapp.ui.detail.DetailActivity

class StoryAdapter :
    PagingDataAdapter<com.example.storyapp.data.local.Story, StoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: ListStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: com.example.storyapp.data.local.Story) {
            Glide.with(itemView.context).load(data.photoUrl).into(binding.ivStory)
            binding.userName.text = data.name
            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("STORY", data)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<com.example.storyapp.data.local.Story>() {
            override fun areItemsTheSame(oldItem: com.example.storyapp.data.local.Story, newItem: com.example.storyapp.data.local.Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: com.example.storyapp.data.local.Story,
                newItem: com.example.storyapp.data.local.Story
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}