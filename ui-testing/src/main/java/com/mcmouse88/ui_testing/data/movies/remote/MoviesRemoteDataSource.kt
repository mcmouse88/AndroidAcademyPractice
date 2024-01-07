package com.mcmouse88.ui_testing.data.movies.remote

import com.mcmouse88.ui_testing.utils.Result
import com.mcmouse88.ui_testing.utils.doOnError
import com.mcmouse88.ui_testing.utils.runOperationCatching
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface MoviesRemoteDataSource {
    suspend fun searchMovies(query: String): Result<List<SearchMovieDto>, Throwable>
}

@Singleton
class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRemoteDataSource {
    override suspend fun searchMovies(query: String): Result<List<SearchMovieDto>, Throwable> {
        return runOperationCatching { moviesApi.searchMovie(query).searchMovies }
            .doOnError { error -> Timber.e( "Search movies from server error", error) }
    }
}
