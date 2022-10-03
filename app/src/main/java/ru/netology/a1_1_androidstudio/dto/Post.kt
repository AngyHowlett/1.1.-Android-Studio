package ru.netology.a1_1_androidstudio.dto

import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val counterLike: Int = 0,
    val likedByMe: Boolean = false,
    val counterRepost: Int = 0,
    val repostByMe: Boolean = false,
    val counterView: Int = 5,


    val views: Int = 0
) {
    var videoUrl: String = ""
}