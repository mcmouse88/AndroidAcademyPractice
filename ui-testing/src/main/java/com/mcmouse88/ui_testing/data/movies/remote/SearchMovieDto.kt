package com.mcmouse88.ui_testing.data.movies.remote

import com.google.gson.annotations.SerializedName

data class SearchMovieDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?
)
