package ru.netology.a1_1_androidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.a1_1_androidstudio.adapter.PostAdapter
import ru.netology.a1_1_androidstudio.databinding.ActivityMainBinding
import ru.netology.a1_1_androidstudio.viewModel.PostViewModel



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter(
            onLikeClicked = { post ->
                viewModel.onLikeClicked(post)
            },
            onShareClicked = { post ->
                viewModel.onShareClicked(post)
            }
        )
        binding.postsRecyclerView.adapter = adapter

        viewModel.data.observe(this) {posts ->
            adapter.submitList(posts)
        }
    }
}