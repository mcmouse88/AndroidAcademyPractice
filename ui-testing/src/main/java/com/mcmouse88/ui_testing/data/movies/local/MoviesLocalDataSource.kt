package com.mcmouse88.ui_testing.data.movies.local

import javax.inject.Inject
import javax.inject.Singleton

interface MoviesLocalDataSource {
    suspend fun insertAll(searchedMovies: List<MovieEntity>)
}

@Singleton
class MoviesLocalDataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : MoviesLocalDataSource {
    override suspend fun insertAll(searchedMovies: List<MovieEntity>) {
        moviesDao.insertAll(searchedMovies)
    }
}
