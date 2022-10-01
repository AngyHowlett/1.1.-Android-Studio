package ru.netology.a1_1_androidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.a1_1_androidstudio.databinding.ActivityMainBinding
import ru.netology.a1_1_androidstudio.dto.PostsAdapter
import ru.netology.a1_1_androidstudio.util.hideKeyboard
import ru.netology.a1_1_androidstudio.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        val adapter = PostsAdapter(viewModel)
        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.saveButton.setOnClickListener {
            with(binding.contentEditText) {
                if(text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Content can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                val content = text.toString()
                viewModel.onSaveListener(content)

                clearFocus()
                hideKeyboard()
            }
        }

        viewModel.currentPost.observe(this) { currentPost ->
            with(binding.contentEditText) {
                val content = currentPost?.content
                setText(content)
                if (content != null) {
                    requestFocus()
                    binding.group.visibility = View.VISIBLE
                    binding.editTextBottom.text = content

                }
            }
        }
        binding.editCancelButton.setOnClickListener {
            viewModel.currentPost.value = null
            binding.group.visibility = View.GONE
            binding.editTextBottom.clearFocus()
            binding.editTextBottom.hideKeyboard()
        }

    }
}