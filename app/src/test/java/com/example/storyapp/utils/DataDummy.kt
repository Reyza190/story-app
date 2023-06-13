package com.example.storyapp.utils


object DataDummy {
    fun generateDummyStories(): List<com.example.storyapp.data.local.Story> {
        val listStory = ArrayList<com.example.storyapp.data.local.Story>()
        for (i in 1..20) {
            val story = com.example.storyapp.data.local.Story(
                id = "id_$i",
                name = "Name $i",
                photoUrl = "https://akcdn.detik.net.id/visual/2020/02/14/066810fd-b6a9-451d-a7ff-11876abf22e2_169.jpeg?w=650",
                description = "Description $i"
            )
            listStory.add(story)
        }

        return listStory
    }
}