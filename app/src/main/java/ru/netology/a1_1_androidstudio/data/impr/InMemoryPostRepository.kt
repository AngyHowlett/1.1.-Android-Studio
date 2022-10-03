package ru.netology.nmedia.data.impr

import androidx.lifecycle.MutableLiveData
import ru.netology.a1_1_androidstudio.data.PostRepository
import ru.netology.a1_1_androidstudio.dto.Post
import ru.netology.a1_1_androidstudio.dto.countLiked
import ru.netology.a1_1_androidstudio.dto.countShared

class InMemoryPostRepository : PostRepository {
    private var posts
        get() = checkNotNull(data.value)
        set(value) {
            data.value = value
        }

    override val data: MutableLiveData<List<Post>>

    init {
        val initialPosts = listOf(
            Post(
                id = 5,
                postName = "Нетология. Университет интернет-профессий",
                postData = "07.08.2022",
                postText = "Привет, это новая Нетология!"
            ),
            Post(
                id = 4,
                postName = "Нетология. Университет интернет-профессий",
                postData = "15.09.2022",
                postText = "Привет, это новая Нетология!"
            ),
            Post(
                id = 3,
                postName = "Нетология. Университет интернет-профессий",
                postData = "22.08.2022",
                postText = "Привет, это новая Нетология!"
            ),
            Post(
                id = 2,
                postName = "Нетология. Университет интернет-профессий",
                postData = "21.07.2022",
                postText = "Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов."
            ),
            Post(
                id = 1,
                postName = "Нетология. Университет интернет-профессий",
                postData = "20.06.2022",
                postText = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен → https://netolo.gy/fyb"
            )
        )
        data = MutableLiveData(initialPosts)
    }

    override fun like(postId: Long) {
        posts = posts.map { post ->
            if (post.id == postId) post.copy(
                likedByMe = !post.likedByMe,
                likes = post.likes,
                countLikeFormat = countLiked(post.likes, !post.likedByMe)
            )
            else post
        }
    }

    override fun share(postId: Long) {
        posts = posts.map { post ->
            if (post.id == postId) post.copy(
                shares = post.shares + 1,
                countShareFormat = countShared(post.shares + 1)
            )
            else post
        }
    }
}