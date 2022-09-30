package ru.netology.a1_1_androidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.a1_1_androidstudio.databinding.ActivityMainBinding
import ru.netology.a1_1_androidstudio.dto.Post
import ru.netology.a1_1_androidstudio.dto.counterView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 0L,
            author = "Вася Пупкин",
            content = "Привет! Это новый Адриано Челентано!",
            published = "07.07.2022",
        )

        with(binding) {
            authorName.text = post.author
            textPost.text = post.content
            date.text = post.published

            likeText.text = counterView(post.counterLike)
            repostText.text = counterView(post.conterRepost)
            viewText.text = counterView(post.counterView)

            if (post.likedByMe) {
                println ("Tst1")
                likeButton.setImageResource(R.drawable.ic_red_favorite_24dp)
            }

            likeButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                println ("Tst2")
                binding.likeButton.setImageResource(
                    if (post.likedByMe) R.drawable.ic_red_favorite_24dp else R.drawable.ic_favorite_24dp
                )

                if (post.likedByMe) post.counterLike++ else post.counterLike--
                likeText.text = counterView(post.counterLike)
            }

            repostButton.setOnClickListener {
                post.conterRepost++
                repostText.text = counterView(post.conterRepost)
            }
        }
    }


}