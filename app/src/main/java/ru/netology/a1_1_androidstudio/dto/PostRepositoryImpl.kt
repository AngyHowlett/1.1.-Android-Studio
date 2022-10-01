package ru.netology.a1_1_androidstudio.dto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryImpl : PostRepository {

    private var nextId = GENERATED_POSTS_AMOUNT.toLong()

    private var posts =
        List(GENERATED_POSTS_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Вася Пупкин",
                content = "Привет! Это новый Вася Пупкин! " +
                        "Очередной $index",
                published = "11.07.2012"
            )
        }

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else {
                if (!it.likedByMe) {
                    it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
                } else {
                    it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
                }
            }
        }
        data.value = posts

    }

    override fun repostById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(counterRepost = it.counterRepost + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        posts = listOf(post.copy(id = ++nextId)) + posts
        data.value = posts
    }

    private fun update(post: Post) {
        posts = posts.map {
            if (it.id == post.id) post else it
        }
        data.value = posts
    }

    private companion object {
        const val GENERATED_POSTS_AMOUNT = 1000
    }


}
