package ru.netology.a1_1_androidstudio.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.a1_1_androidstudio.dto.PostRepository
import ru.netology.a1_1_androidstudio.dto.PostRepositoryImpl


class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()

    val data by repository::data

    fun onLikeClicked() = repository.like()
    fun onRepostClicked() = repository.repost()
}