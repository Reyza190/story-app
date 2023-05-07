package com.example.storyapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapp.data.api.ListStoryItem
import com.example.storyapp.data.api.Story
import com.example.storyapp.databinding.ListStoryBinding
import com.example.storyapp.ui.detail.DetailActivity

//class StoryAdapter(private val listStory: List<ListStoryItem>):RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
//    class ViewHolder(var binding : ListStoryBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return listStory.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val story = listStory[position]
//        Glide.with(holder.itemView.context).load(story.photoUrl).into(holder.binding.ivStory)
//        holder.binding.userName.text = story.name
//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, DetailActivity::class.java)
//            intent.putExtra("STORY", story)
//            it.context.startActivity(intent)
//        }
//    }
//}

class StoryAdapter :
    PagingDataAdapter<ListStoryItem, StoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: ListStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListStoryItem) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryAdapter.ViewHolder {
        val binding = ListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}