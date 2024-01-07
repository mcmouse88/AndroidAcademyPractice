package com.mcmouse88.ui_testing.domain.repository

import com.mcmouse88.ui_testing.domain.movies.SearchMovieWithMyReview
import com.mcmouse88.ui_testing.utils.Result

interface MoviesRepository {
    suspend fun searchMoviesWithReviews(query: String): Result<List<SearchMovieWithMyReview>, Throwable>
}