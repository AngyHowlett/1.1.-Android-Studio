package ru.netology.a1_1_androidstudio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.a1_1_androidstudio.R
import ru.netology.a1_1_androidstudio.databinding.PostBinding
import ru.netology.a1_1_androidstudio.dto.Post
import ru.netology.a1_1_androidstudio.dto.counterView
import ru.netology.a1_1_androidstudio.util.UrlParse


class PostsAdapter(
    private val interactionListener: PostInteractionListener
) : ListAdapter<Post, PostsAdapter.PostViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

class PostViewHolder(
    private val binding: PostBinding,
    private val interactionListener: PostInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var post: Post

    private val popupMenu by lazy {
        PopupMenu(itemView.context, binding.menuButton).apply {
            inflate(R.menu.options_post)
            setOnMenuItemClickListener { option ->
                when (option.itemId) {
                    R.id.remove -> {
                        interactionListener.onRemoveListener(post)
                        true
                    }
                    R.id.edit -> {
                        interactionListener.onEditListener(post)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    init {
        binding.likeButton.setOnClickListener {
            interactionListener.onLikeListener(post)
        }

        binding.repostButton.setOnClickListener {
            interactionListener.onRepostListener(post)
        }

        binding.menuButton.setOnClickListener {
            popupMenu.show()
        }

        binding.videoPlayMaterialButton.setOnClickListener {
            interactionListener.onVideoPlayButtonClicked(post)
        }

        binding.videoBannerImageButton.setOnClickListener {
            interactionListener.onVideoBannerClicked(post)
        }

        binding.root.setOnClickListener {
            interactionListener.onPostClicked(post)
        }

    }

        fun bind(post: Post) {
            this.post = post
            binding.apply {
                authorName.text = post.author
                textPost.text = post.content
                date.text = post.published
                likeButton.text = counterView(post.counterLike)
                likeButton.isChecked = post.likedByMe
                repostButton.text = counterView(post.counterRepost)
                repostButton.isChecked = post.repostByMe
                viewButton.text = counterView(post.counterView)


                val urlList = UrlParse.getHyperLinks(textPost.text.toString())
                for (link in urlList) {
                    if (link.contains("vkontakte") || link.contains("vk.com")) {
                        post.videoUrl = link
                    } else {
                        post.videoUrl = ""
                    }
                }
                if (post.videoUrl.isEmpty()) {
                    videoContentGroup.visibility = View.GONE
                } else {
                    videoContentGroup.visibility = View.VISIBLE
                }
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }
}