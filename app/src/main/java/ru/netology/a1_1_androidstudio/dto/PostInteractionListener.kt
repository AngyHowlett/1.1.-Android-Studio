package ru.netology.a1_1_androidstudio.dto

interface PostInteractionListener {

    fun onLikeListener(post: Post)

    fun onRepostListener(post: Post)

    fun onRemoveListener(post: Post)

    fun onEditListener(post: Post)

}