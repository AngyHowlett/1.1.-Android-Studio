package ru.netology.a1_1_androidstudio.db

import ru.netology.a1_1_androidstudio.dto.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun repostById(id: Long)
}