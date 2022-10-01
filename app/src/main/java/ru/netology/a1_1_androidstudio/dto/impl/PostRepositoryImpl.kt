package ru.netology.a1_1_androidstudio.dto.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.a1_1_androidstudio.dto.Post
import ru.netology.a1_1_androidstudio.dto.PostRepository

class PostRepositoryImpl : PostRepository {

    private var nextId = GENERATED_POSTS_AMOUNT.toLong()

    override val data = MutableLiveData(
        List(GENERATED_POSTS_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Адриано Челентано",
                content = "Привет! Это новый Адриано Челентано! Очередной $index",
                published = "21.05.2022"
            )
        }
    )

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override fun likeById(id: Long) {
        val newListPost = posts.map {
            if (it.id != id) it else {
                if (!it.likedByMe) {
                    it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
                } else {
                    it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
                }
            }
        }
        data.value = newListPost

    }

    override fun repostById(id: Long) {
        val newListPost = posts.map {
            if (it.id != id) it else it.copy(counterRepost = it.counterRepost + 1)
        }
        data.value = newListPost
    }

    override fun removeById(id: Long) {
        val newListPost = posts.filter { it.id != id }
        data.value = newListPost
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        val newListPost = listOf(post.copy(id = ++nextId)) + posts
        data.value = newListPost
    }

    override fun update(post: Post) {
        val newListPost = posts.map {
            if (it.id == post.id) post else it
        }
        data.value = newListPost
    }

    private companion object {
        const val GENERATED_POSTS_AMOUNT = 1000
    }


}
