package ru.netology.a1_1_androidstudio.data.impl

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.a1_1_androidstudio.data.PostRepository
import ru.netology.a1_1_androidstudio.dto.Post
import kotlin.properties.Delegates

class FilePostRepository(
   private val application: Application
) : PostRepository {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type


    private val prefs = application.getSharedPreferences(
        "repo", Context.MODE_PRIVATE
    )

    private var nextId: Long by Delegates.observable(
        prefs.getLong(NEXT_ID_PREFS_KEY, 0L)
    ) {_, _, newValue ->
        prefs.edit { putLong(NEXT_ID_PREFS_KEY, newValue)}
    }

    override val data: MutableLiveData<List<Post>>

    init {
        val postsFile = application.filesDir.resolve(FILE_NAME)
        val posts: List<Post> = if (postsFile.exists()) {
            val inputStream = application.openFileInput(FILE_NAME)
            val reader = inputStream.bufferedReader()
            reader.use { gson.fromJson(it, type) }
        } else emptyList()
        data = MutableLiveData(posts)
    }


    private var posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }
        set(value) {
            application.openFileOutput(
                FILE_NAME, Context.MODE_PRIVATE
            ).bufferedWriter().use {
                it.write(gson.toJson(value))
            }
            data.value = value
        }

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

    }

    override fun repostById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(counterRepost = it.counterRepost + 1)
        }
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        posts = listOf(post.copy(id = ++nextId)) + posts
    }

    private fun update(post: Post) {
        posts = posts.map {
            if (it.id == post.id) post else it
        }
    }

    private companion object {
        const val NEXT_ID_PREFS_KEY = "nextId"
        const val FILE_NAME = "posts.json"
    }


}
