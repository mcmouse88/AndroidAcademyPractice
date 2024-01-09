package com.mcmouse88.ui_testing.presentation.movies

import com.mcmouse88.ui_testing.domain.movies.SearchMovieWithMyReview

data class MoviesResult(
    val query: String = "",
    val isMovieLoading: Boolean = false,
    val resultPlaceholder: Int? = null,
    val movies: List<SearchMovieWithMyReview> = emptyList()
)
