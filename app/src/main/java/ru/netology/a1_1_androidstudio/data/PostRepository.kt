package ru.netology.a1_1_androidstudio.data

import androidx.lifecycle.LiveData
import ru.netology.a1_1_androidstudio.dto.Post


interface PostRepository {
    val data: LiveData<List<Post>>

    fun like(postId: Long)

    fun share(postId: Long)
}

