package com.mcmouse88.ui_testing.data.review.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mcmouse88.ui_testing.domain.movies.MovieId
import com.mcmouse88.ui_testing.domain.movies.Rating

@Entity(tableName = MyReviewEntity.TABLE_NAME)
data class MyReviewEntity(
    @[PrimaryKey ColumnInfo(name = REVIEW_ID)]
    val reviewId: String,
    @ColumnInfo(name = REVIEW_MOVIE_ID)
    val reviewMovieId: MovieId,
    @ColumnInfo(name = LIKED)
    val liked: String,
    @ColumnInfo(name = DISLIKED)
    val disliked: String,
    @ColumnInfo(name = RATING)
    val rating: Rating
) {
    companion object {
        const val TABLE_NAME = "my_reviews"
        const val REVIEW_ID = "review_id"
        const val REVIEW_MOVIE_ID = "reviewMovieId"
        const val LIKED = "liked"
        const val DISLIKED = "disliked"
        const val RATING = "rating"
    }
}
