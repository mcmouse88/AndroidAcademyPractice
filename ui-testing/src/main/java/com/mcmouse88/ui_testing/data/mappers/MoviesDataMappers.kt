package com.mcmouse88.ui_testing.data.mappers

import com.mcmouse88.ui_testing.data.movies.local.MovieEntity
import com.mcmouse88.ui_testing.data.movies.remote.SearchMovieDto
import com.mcmouse88.ui_testing.data.review.local.MyReviewEntity
import com.mcmouse88.ui_testing.domain.movies.MovieId
import com.mcmouse88.ui_testing.domain.movies.MyReview
import com.mcmouse88.ui_testing.domain.movies.Review

internal fun SearchMovieDto.toEntity(baseImageUrl: String): MovieEntity {
    return MovieEntity(
        id = MovieId(this.id),
        title = this.title,
        thumbnail = getPosterUrl(baseImageUrl, this.posterPath),
        imdbId = "",
        overview = "",
        allowedAge = "",
        rating = 0,
        reviewCounter = 0,
        popularity = 0.00f,
        releaseDate = "",
        duration = 0,
        budget = 0,
        revenue = 0,
        status = "Released",
        genres = "",
        homePage = ""
    )
}

private fun getPosterUrl(baseImageUrl: String, posterPath: String?): String {
    return "$baseImageUrl$posterPath"
}

internal fun MyReviewEntity.toModel(): MyReview {
    return MyReview(
        movieId = this.reviewMovieId,
        review = Review(
            id = Review.Id(value = this.reviewId),
            liked = this.liked,
            disliked = this.disliked,
            rating = this.rating
        )
    )
}