package com.mcmouse88.ui_testing.data.repository

import com.mcmouse88.ui_testing.data.core.MovieUrlProvider
import com.mcmouse88.ui_testing.data.mappers.toEntity
import com.mcmouse88.ui_testing.data.mappers.toModel
import com.mcmouse88.ui_testing.data.mappers.toSearchMovie
import com.mcmouse88.ui_testing.data.movies.local.MoviesLocalDataSource
import com.mcmouse88.ui_testing.data.movies.remote.MoviesRemoteDataSource
import com.mcmouse88.ui_testing.data.review.local.MyReviewsLocalDataSource
import com.mcmouse88.ui_testing.domain.movies.MovieId
import com.mcmouse88.ui_testing.domain.movies.MyReview
import com.mcmouse88.ui_testing.domain.movies.SearchMovie
import com.mcmouse88.ui_testing.domain.movies.SearchMovieWithMyReview
import com.mcmouse88.ui_testing.domain.repository.MoviesRepository
import com.mcmouse88.ui_testing.utils.Result
import com.mcmouse88.ui_testing.utils.doOnSuccess
import com.mcmouse88.ui_testing.utils.mapSuccess
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val myReviewsLocalDataSource: MyReviewsLocalDataSource,
    private val movieUrlProvider: MovieUrlProvider
) : MoviesRepository {

    override suspend fun searchMoviesWithReviews(query: String): Result<List<SearchMovieWithMyReview>, Throwable> {
        return searchMovies(query)
            .mapSuccess { movies -> fillSearchMoviesWithMyReview(movies) }
    }

    private suspend fun searchMovies(query: String): Result<List<SearchMovie>, Throwable> {
        return moviesRemoteDataSource.searchMovies(query)
            .mapSuccess { dtoList -> dtoList.map { dto -> dto.toEntity(movieUrlProvider.baseImageUrl) } }
            .doOnSuccess { movieEntities -> moviesLocalDataSource.insertAll(movieEntities) }
            .mapSuccess { entries -> entries.map { movieEntity -> movieEntity.toSearchMovie() } }
    }

    private suspend fun fillSearchMoviesWithMyReview(movies: List<SearchMovie>): List<SearchMovieWithMyReview> {
        val moviesIds = movies.map { singleMovie -> singleMovie.id }
        val myReview = getMyReviewsForMovies(moviesIds)

        return movies.map { singleMovie ->
            SearchMovieWithMyReview(
                movie = singleMovie,
                myReview = myReview[singleMovie.id]
            )
        }
    }

    private suspend fun getMyReviewsForMovies(movieIds: List<MovieId>): Map<MovieId, MyReview> {
        return myReviewsLocalDataSource.getMovieByIds(movieIds)
            .map { myReviewEntity -> myReviewEntity.toModel() }
            .associateBy { myReview -> myReview.movieId }
    }
}