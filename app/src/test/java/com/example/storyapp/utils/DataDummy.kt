package com.example.storyapp.utils

import com.example.storyapp.data.api.*

object DataDummy {
    fun generateDummyStories(): List<ListStoryItem> {
        val listStory = ArrayList<ListStoryItem>()
        for (i in 1..20) {
            val story = ListStoryItem(
                createdAt = "2022-02-22T22:22:22Z",
                description = "Description $i",
                id = "id_$i",
                lat = i.toDouble() * 10,
                lon = i.toDouble() * 10,
                name = "Name $i",
                photoUrl = "https://akcdn.detik.net.id/visual/2020/02/14/066810fd-b6a9-451d-a7ff-11876abf22e2_169.jpeg?w=650"
            )
            listStory.add(story)
        }

        return listStory
    }
}