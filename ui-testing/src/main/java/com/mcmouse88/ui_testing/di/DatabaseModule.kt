package com.mcmouse88.ui_testing.di

import android.content.Context
import com.mcmouse88.ui_testing.data.core.db.MoviesDb
import com.mcmouse88.ui_testing.data.movies.local.MoviesDao
import com.mcmouse88.ui_testing.data.review.local.MyReviewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DatabaseModule {

    @[Provides Singleton]
    fun provideMoviesDb(
        @ApplicationContext context: Context
    ): MoviesDb = MoviesDb.create(context)

    @[Provides Singleton]
    fun provideMoviesDao(
        moviesDb: MoviesDb
    ): MoviesDao = moviesDb.moviesDao()

    @[Provides Singleton]
    fun provideMyReviewDao(
        moviesDb: MoviesDb
    ): MyReviewsDao = moviesDb.myReviewDao()
}