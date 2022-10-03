package ru.netology.a1_1_androidstudio.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
class PostEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "published")
    val published: String,

    @ColumnInfo(name = "likedByMe")
    val likedByMe: Boolean = false,

    @ColumnInfo(name = "counterLike")
    val counterLike: Int = 0,

    @ColumnInfo(name = "counterRepost")
    val counterRepost: Int = 0,

    @ColumnInfo(name = "repostByMe")
    val repostByMe: Boolean = false,

    @ColumnInfo(name = "counterView")
    val counterView: Int = 5,

    @ColumnInfo(name = "views")
    val views: Int = 0

) {
}