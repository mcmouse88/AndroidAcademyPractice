package com.mcmouse88.ui_testing.domain.movies

data class SearchMovie(
    val id: MovieId,
    val title: String,
    val thumbnail: String?
)

@JvmInline
value class MovieId(
    val value: Long
)