package ru.netology.a1_1_androidstudio.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var counterLike: Int = 0,
    var counterRepost: Int = 0,
    var counterView: Int = 0,
    var likedByMe: Boolean = false
)