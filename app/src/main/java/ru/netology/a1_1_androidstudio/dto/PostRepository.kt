package ru.netology.a1_1_androidstudio.dto

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<List<Post>>

    fun likeById(id: Long)

    fun repostById(id: Long)

    fun removeById(id: Long)

    fun save(post: Post)

    companion object {
        const val NEW_POST_ID = 0L
    }
}