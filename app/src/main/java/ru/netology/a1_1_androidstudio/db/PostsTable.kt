package ru.netology.a1_1_androidstudio.db

object PostsTable {

    const val NAME = "posts"

    val DDL = """
        CREATE TABLE $NAME (
            ${Column.ID.columnName} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${Column.AUTHOR.columnName} TEXT NOT NULL,
            ${Column.CONTENT.columnName} TEXT NOT NULL,
            ${Column.PUBLISHED.columnName} TEXT NOT NULL,
            ${Column.LIKED_BY_ME.columnName} BOOLEAN NOT NULL DEFAULT 0,
            ${Column.LIKES.columnName} INTEGER NOT NULL DEFAULT 0,
            ${Column.REPOST_BY_ME.columnName} BOOLEAN NOT NULL DEFAULT 0,
            ${Column.REPOST.columnName} INTEGER NOT NULL DEFAULT 0,
            ${Column.VIEWS.columnName} INTEGER NOT NULL DEFAULT 0
        );
        """.trimIndent()

    val ALL_COLUMNS_NAMES = Column.values().map {
        it.columnName
    }.toTypedArray()

    enum class Column(val columnName: String) {
        ID("id"),
        AUTHOR("author"),
        CONTENT("content"),
        PUBLISHED("published"),
        LIKED_BY_ME("likedByMe"),
        LIKES("likes"),
        REPOST_BY_ME("repostByMe"),
        REPOST("repost"),
        VIEWS("views")
    }

}