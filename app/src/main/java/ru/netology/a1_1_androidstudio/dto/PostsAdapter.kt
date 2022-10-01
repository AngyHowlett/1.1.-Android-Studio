package ru.netology.a1_1_androidstudio.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.a1_1_androidstudio.R
import ru.netology.a1_1_androidstudio.databinding.PostBinding


class PostsAdapter(
    private val interactionListener: PostInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: PostBinding,
    private val listener: PostInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            authorName.text = post.author
            textPost.text = post.content
            date.text = post.published

            likeText.text = counterView(post.counterLike)
            repostText.text = counterView(post.counterRepost)
            viewText.text = counterView(post.counterView)

            likeButton.setImageResource(getLikeIconRes(post.likedByMe))

            likeButton.setOnClickListener {
                listener.onLikeListener(post)
            }

            repostButton.setOnClickListener {
                listener.onRepostListener(post)
            }

            menuButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listener.onRemoveListener(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEditListener(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

}


@DrawableRes
private fun getLikeIconRes(liked: Boolean) =
    if (liked) R.drawable.ic_red_favorite_24dp else R.drawable.ic_favorite_24dp

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}