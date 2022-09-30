package ru.netology.a1_1_androidstudio.dto

import androidx.lifecycle.LiveData

interface PostRepository {
    val data: LiveData<Post>

    fun like()

    fun repost()
}