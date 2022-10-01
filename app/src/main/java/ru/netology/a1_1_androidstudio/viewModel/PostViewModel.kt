package ru.netology.a1_1_androidstudio.viewModel


import SingleLiveEvent
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.a1_1_androidstudio.dto.*
import ru.netology.a1_1_androidstudio.dto.impl.FilePostRepository

class PostViewModel(
    application: Application
): AndroidViewModel(application),
    PostInteractionListener {
    private val repository: PostRepository =
        FilePostRepository(application)

    val data by repository::data

    val repostPostContent = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<Unit>()
    val videoUrl = SingleLiveEvent<String>()
    val currentPost = MutableLiveData<Post?>(null)

    fun onSaveListener(content: String) {
        val post = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Иван Петрович",
            content = content,
            published = "10.12.2021"
        )
        repository.save(post)
        currentPost.value = null
    }

    fun onAddClicked() {
        currentPost.value = null
        navigateToPostContentScreenEvent.call()
    }

    override fun onLikeListener(post: Post) = repository.likeById(post.id)

    override fun onRepostListener(post: Post) {
        repostPostContent.value = post.content
        repository.repostById(post.id)
    }
    override fun onRemoveListener(post: Post) = repository.removeById(post.id)

    override fun onEditListener(post: Post) {
        currentPost.value = post
        navigateToPostContentScreenEvent.call()
    }

    override fun onVideoPlayButtonClicked(post: Post) {
        videoUrl.value = post.videoUrl
    }

    override fun onVideoBannerClicked(post: Post) {
        videoUrl.value = post.videoUrl
    }

}