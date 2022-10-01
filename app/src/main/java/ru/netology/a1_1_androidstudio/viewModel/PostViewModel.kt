package ru.netology.a1_1_androidstudio.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.a1_1_androidstudio.dto.Post
import ru.netology.a1_1_androidstudio.dto.PostInteractionListener
import ru.netology.a1_1_androidstudio.dto.PostRepository
import ru.netology.a1_1_androidstudio.dto.PostRepositoryImpl

class PostViewModel: ViewModel(), PostInteractionListener {
    private val repository: PostRepository = PostRepositoryImpl()

    val data = repository.get()

    val currentPost = MutableLiveData<Post?>(null)

    fun onSaveListener(content: String) {
        val post = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Масимо Каррера",
            content = content,
            published = "21.05.2022"
        )
        repository.save(post)
        currentPost.value = null
    }

    override fun onLikeListener(post: Post) = repository.likeById(post.id)
    override fun onRepostListener(post: Post) = repository.repostById(post.id)
    override fun onRemoveListener(post: Post) = repository.removeById(post.id)
    override fun onEditListener(post: Post) {
        currentPost.value = post
    }

}