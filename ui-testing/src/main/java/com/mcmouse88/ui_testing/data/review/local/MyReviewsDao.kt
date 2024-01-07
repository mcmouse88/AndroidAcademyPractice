package com.mcmouse88.ui_testing.data.review.local

import androidx.room.Dao
import androidx.room.Query
import com.mcmouse88.ui_testing.domain.movies.MovieId

@Dao
interface MyReviewsDao {

    @Query("""
        SELECT * FROM ${MyReviewEntity.TABLE_NAME}
        WHERE ${MyReviewEntity.REVIEW_MOVIE_ID} IN (:movieIds)
    """)
    suspend fun getMoviesByIds(movieIds: List<MovieId>): List<MyReviewEntity>
}
