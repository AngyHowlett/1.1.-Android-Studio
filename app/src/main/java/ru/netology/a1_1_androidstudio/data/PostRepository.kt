package ru.netology.a1_1_androidstudio.data

import androidx.lifecycle.LiveData
import ru.netology.a1_1_androidstudio.dto.Post


interface PostRepository {
    val data: LiveData<List<Post>>

    fun likeById(id: Long)

    fun repostById(id: Long)

    fun removeById(id: Long)

    fun save(post: Post)


    companion object {
        const val NEW_POST_ID = 0L
    }
}
