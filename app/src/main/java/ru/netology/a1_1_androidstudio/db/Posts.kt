package ru.netology.a1_1_androidstudio.db

import android.database.Cursor
import ru.netology.a1_1_androidstudio.dto.Post

fun Cursor.toPost() = Post(
    id = getLong(getColumnIndexOrThrow(PostsTable.Column.ID.columnName)),
    author = getString(getColumnIndexOrThrow(PostsTable.Column.AUTHOR.columnName)),
    content = getString(getColumnIndexOrThrow(PostsTable.Column.CONTENT.columnName)),
    published = getString(getColumnIndexOrThrow(PostsTable.Column.PUBLISHED.columnName)),
    likedByMe = getInt(getColumnIndexOrThrow(PostsTable.Column.LIKED_BY_ME.columnName)) != 0,
    counterLike = getInt(getColumnIndexOrThrow(PostsTable.Column.LIKES.columnName)),
    repostByMe = getInt(getColumnIndexOrThrow(PostsTable.Column.REPOST_BY_ME.columnName)) != 0,
    counterRepost = getInt(getColumnIndexOrThrow(PostsTable.Column.REPOST.columnName)),
    counterView = getInt(getColumnIndexOrThrow(PostsTable.Column.VIEWS.columnName))
)