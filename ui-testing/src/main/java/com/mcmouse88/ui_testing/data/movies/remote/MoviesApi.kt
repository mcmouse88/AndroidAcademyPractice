package com.mcmouse88.ui_testing.data.movies.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchMovieResponse
}
