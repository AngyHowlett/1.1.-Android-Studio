package ru.netology.a1_1_androidstudio.dto

const val k = 1_000
const val m = 1_000_000

fun countLiked(likes: Int, likedByMe: Boolean): Any {

    var countLikes = likes

    val likedFormat = when (val liked = if (likedByMe) ++countLikes else countLikes) {
        in 0..999 -> {
            "$liked"
        }
        in 2_000..2_099, in 3_000..3_099, in 4_000..4_099,
        in 5_000..5_099, in 6_000..6_099, in 7_000..7_099,
        in 8_000..8_099, in 9_000..9_099 -> {
            "${liked / k}k"
        }
        in 1_100..9_999 -> {
            "${liked / k}.${(liked / 100) % 10}k"
        }
        in 10_000..999_999 -> {
            "${liked / k}k"
        }
        in 1_000_000..1_000_099,
        in 2_000_000..2_000_099,
        in 3_000_000..3_099_999,
        in 4_000_000..4_099_999,
        in 5_000_000..5_099_999,
        in 6_000_000..6_099_999,
        in 7_000_000..7_099_999,
        in 8_000_000..8_099_999,
        in 9_000_000..9_099_999 -> {
            "${liked / m}m"
        }
        in 10_000_000..999_999_999 -> {
            "${liked / m}m"
        }
        else -> "${liked / k}k"
    }
    return likedFormat
}

fun countShared(shares: Int): Any {

    val shareFormat = when (shares) {
        in 0..999 -> {
            "$shares"
        }
        in 2_000..2_099, in 3_000..3_099, in 4_000..4_099,
        in 5_000..5_099, in 6_000..6_099, in 7_000..7_099,
        in 8_000..8_099, in 9_000..9_099 -> {
            "${shares / k}k"
        }
        in 1_100..9_999 -> {
            "${shares / k}.${(shares / 100) % 10}k"
        }
        in 10_000..999_999 -> {
            "${shares / k}k"
        }
        in 1_000_000..1_000_099,
        in 2_000_000..2_000_099,
        in 3_000_000..3_099_999,
        in 4_000_000..4_099_999,
        in 5_000_000..5_099_999,
        in 6_000_000..6_099_999,
        in 7_000_000..7_099_999,
        in 8_000_000..8_099_999,
        in 9_000_000..9_099_999 -> {
            "${shares / m}m"
        }
        in 10_000_000..999_999_999 -> {
            "${shares / m}m"
        }
        else -> "${shares / k}k"
    }
    return shareFormat
}