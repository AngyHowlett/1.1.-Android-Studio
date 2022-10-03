package ru.netology.a1_1_androidstudio.db


import ru.netology.a1_1_androidstudio.dto.Post

internal fun PostEntity.toModel() = Post(
    id = id,
    author = author,
    content = content,
    published = published,
    likedByMe = likedByMe,
    counterLike = counterLike,
    repostByMe = repostByMe,
    counterRepost = counterRepost,
    counterView = counterView
)

internal fun Post.toEntity() = PostEntity(
    id = id,
    author = author,
    content = content,
    published = published,
    likedByMe = likedByMe,
    counterLike = counterLike,
    repostByMe = repostByMe,
    counterRepost = counterRepost,
    counterView = counterView
)