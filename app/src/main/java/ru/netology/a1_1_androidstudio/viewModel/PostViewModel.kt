package ru.netology.a1_1_androidstudio.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.a1_1_androidstudio.data.PostRepository
import ru.netology.a1_1_androidstudio.dto.Post
import ru.netology.nmedia.data.impr.InMemoryPostRepository


class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()

    val data get() = repository.data

    fun onLikeClicked(post: Post) = repository.like(post.id)

    fun onShareClicked(post: Post) = repository.share(post.id)

}