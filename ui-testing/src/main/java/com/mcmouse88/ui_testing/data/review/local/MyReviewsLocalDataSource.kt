package com.mcmouse88.ui_testing.data.review.local

import com.mcmouse88.ui_testing.domain.movies.MovieId
import javax.inject.Inject
import javax.inject.Singleton

interface MyReviewsLocalDataSource {
    suspend fun getMovieByIds(movieIds: List<MovieId>): List<MyReviewEntity>
}

@Singleton
class MyReviewsLocalDataSourceImpl @Inject constructor(
    private val myReviewsDao: MyReviewsDao
) : MyReviewsLocalDataSource {
    override suspend fun getMovieByIds(movieIds: List<MovieId>): List<MyReviewEntity> {
        return myReviewsDao.getMoviesByIds(movieIds)
    }
}
