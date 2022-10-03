package ru.netology.a1_1_androidstudio.data.impl


import androidx.lifecycle.map
import ru.netology.a1_1_androidstudio.data.PostRepository
import ru.netology.a1_1_androidstudio.db.PostDao
import ru.netology.a1_1_androidstudio.db.toEntity
import ru.netology.a1_1_androidstudio.db.toModel
import ru.netology.a1_1_androidstudio.dto.Post


class PostRepositoryImpl(
    private val dao: PostDao
) : PostRepository {

    override val data = dao.getAll().map { entities ->
        entities.map { it.toModel()}
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
    }

    override fun repostById(id: Long) {
        dao.repostById(id)
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun save(post: Post) {
        if (post.id == 0L) dao.insert(post.toEntity()) else
            dao.updateContentById(post.id, post.content)
    }

}