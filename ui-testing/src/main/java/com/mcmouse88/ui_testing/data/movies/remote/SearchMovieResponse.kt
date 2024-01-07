package com.mcmouse88.ui_testing.data.movies.remote

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val searchMovies: List<SearchMovieDto>
)
