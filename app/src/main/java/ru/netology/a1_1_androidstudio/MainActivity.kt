package ru.netology.a1_1_androidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.activity.viewModels
import ru.netology.a1_1_androidstudio.databinding.ActivityMainBinding
import ru.netology.a1_1_androidstudio.dto.counterView
import ru.netology.a1_1_androidstudio.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                authorName.text = post.author
                textPost.text = post.content
                date.text = post.published

                likeText.text = counterView(post.counterLike)
                repostText.text = counterView(post.counterRepost)
                viewText.text = counterView(post.counterView)

                likeButton.setImageResource(getLikeIconRes(post.likedByMe))

                likeButton.setOnClickListener {
                    viewModel.onLikeClicked()
                }

                repostButton.setOnClickListener {
                    viewModel.onRepostClicked()
                }
            }
        }
    }

    @DrawableRes
    private fun getLikeIconRes(liked: Boolean) =
        if (liked) R.drawable.ic_red_favorite_24dp else R.drawable.ic_favorite_24dp
}