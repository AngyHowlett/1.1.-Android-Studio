package ru.netology.a1_1_androidstudio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.a1_1_androidstudio.R
import ru.netology.a1_1_androidstudio.databinding.PostListBinding
import ru.netology.a1_1_androidstudio.dto.Post

class PostAdapter(
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit
) : ListAdapter<Post, PostAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostListBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding, onLikeClicked, onShareClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class ViewHolder(
        private val binding: PostListBinding,
        private val onLikeClicked: (Post) -> Unit,
        private val onShareClicked: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        init {
            binding.like.setOnClickListener {
                onLikeClicked(post)
            }
            binding.share.setOnClickListener {
                onShareClicked(post)
            }
        }

        fun bind(post: Post) {
            this.post = post

            with(binding) {
                postName.text = post.postName
                postData.text = post.postData
                postText.text = post.postText
                countLike.text = post.countLikeFormat.toString()
                countShare.text = post.countShareFormat.toString()
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_red_like else R.drawable.ic_like
                )
            }
        }
    }

    private object DiffCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem

    }
}