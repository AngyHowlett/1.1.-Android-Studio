package ru.netology.a1_1_androidstudio.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import ru.netology.a1_1_androidstudio.dto.Post

class PostDaoImpl(
    private val db: SQLiteDatabase
) : PostDao {

    override fun getAll() = db.query(
        PostsTable.NAME,
        PostsTable.ALL_COLUMNS_NAMES,
        null, null, null, null,
        "${PostsTable.Column.ID.columnName} DESC"
    ).use { cursor ->
        List(cursor.count) {
            cursor.moveToNext()
            cursor.toPost()
        }
    }

    override fun save(post: Post): Post {
        val values = ContentValues().apply {
            put(PostsTable.Column.AUTHOR.columnName, post.author)
            put(PostsTable.Column.CONTENT.columnName, post.content)
            put(PostsTable.Column.PUBLISHED.columnName, post.published)
        }
        val id = if (post.id != 0L) {
            db.update(
                PostsTable.NAME,
                values,
                "${PostsTable.Column.ID.columnName} = ?",
                arrayOf(post.id.toString())
            )
            post.id
        } else {
            db.insert(PostsTable.NAME, null, values)
        }
        return db.query(
            PostsTable.NAME,
            PostsTable.ALL_COLUMNS_NAMES,
            "${PostsTable.Column.ID.columnName} = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { cursor ->
            cursor.moveToNext()
            cursor.toPost()
        }
    }


    override fun likeById(id: Long) {
        db.execSQL(
                """
                UPDATE ${PostsTable.NAME} SET
                    likes = likes + CASE WHEN likedByMe THEN -1 ELSE 1 END,
                    likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
                WHERE id = ?;
                """.trimIndent(),
            arrayOf(id)
        )
    }


    override fun removeById(id: Long) {
        db.delete(
            PostsTable.NAME,
            "${PostsTable.Column.ID.columnName} = ?",
            arrayOf(id.toString())
        )
    }

    override fun repostById(id: Long) {
        db.execSQL(
            """
                UPDATE ${PostsTable.NAME} SET
                    repost = repost + CASE WHEN repostByMe THEN -1 ELSE 1 END,
                    repostByMe = CASE WHEN repostByMe THEN 0 ELSE 1 END
                WHERE id = ?;
                """.trimIndent(),
            arrayOf(id)
        )
    }


}