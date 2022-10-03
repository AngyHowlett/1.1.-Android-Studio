package ru.netology.a1_1_androidstudio.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.a1_1_androidstudio.data.PostRepository
import ru.netology.a1_1_androidstudio.db.PostDao
import ru.netology.a1_1_androidstudio.dto.Post

class SQLiteRepository(
    private val dao: PostDao
) : PostRepository {

    override val data = MutableLiveData(dao.getAll())

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override fun likeById(id: Long) {
        dao.likeById(id)
        data.value = posts.map {
            if (it.id != id) it else {
                if (!it.likedByMe) {
                    it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
                } else {
                    it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
                }
            }
        }
    }

    override fun repostById(id: Long) {
        dao.repostById(id)
        data.value = posts.map {
            if (it.id != id) it else it.copy(
                counterRepost = it.counterRepost + 1,
                repostByMe = true
            )
        }
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
        data.value = posts.filter { it.id != id }
    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        data.value = if (id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
    }

}