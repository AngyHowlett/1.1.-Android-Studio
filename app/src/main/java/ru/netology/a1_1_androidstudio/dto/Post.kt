package ru.netology.a1_1_androidstudio.dto


data class Post(
    val id: Long,
    val postName: String,
    val postData: String,
    val postText: String,
    val likedByMe: Boolean = false,
    val likes: Int = 0,
    val countLikeFormat: Any = countLiked(likes, likedByMe),
    var shares: Int = 0,
    val countShareFormat: Any = countShared(shares)
)