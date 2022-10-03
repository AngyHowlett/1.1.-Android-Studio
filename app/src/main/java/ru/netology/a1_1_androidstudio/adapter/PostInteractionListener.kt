package ru.netology.a1_1_androidstudio.adapter

import ru.netology.a1_1_androidstudio.dto.Post

interface PostInteractionListener {

    fun onLikeListener(post: Post)

    fun onRepostListener(post: Post)

    fun onRemoveListener(post: Post)

    fun onEditListener(post: Post)

    fun onVideoPlayButtonClicked(post: Post)

    fun onVideoBannerClicked(post: Post)

    fun onPostClicked(post: Post)

}