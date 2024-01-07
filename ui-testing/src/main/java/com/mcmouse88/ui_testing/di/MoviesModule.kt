package com.mcmouse88.ui_testing.di

import com.mcmouse88.ui_testing.data.movies.local.MoviesLocalDataSource
import com.mcmouse88.ui_testing.data.movies.local.MoviesLocalDataSourceImpl
import com.mcmouse88.ui_testing.data.movies.remote.MoviesRemoteDataSource
import com.mcmouse88.ui_testing.data.movies.remote.MoviesRemoteDataSourceImpl
import com.mcmouse88.ui_testing.data.repository.MoviesRepositoryImpl
import com.mcmouse88.ui_testing.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface MoviesModule {

    @[Binds Singleton]
    fun bindMoviesRemoteDataSource(
        impl: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @[Binds Singleton]
    fun bindMoviesLocalDataSource(
        impl: MoviesLocalDataSourceImpl
    ): MoviesLocalDataSource

    @[Binds Singleton]
    fun bindMoviesRepository(
        impl: MoviesRepositoryImpl
    ): MoviesRepository
}