package com.mcmouse88.ui_testing.domain.movies

data class MyReview(
    val movieId: MovieId,
    val review: Review
)

data class Review(
    val id: Id,
    val liked: String,
    val disliked: String,
    val rating: Rating
) {
    @JvmInline
    value class Id(val value: String)
}
