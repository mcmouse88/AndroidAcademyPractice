package com.mcmouse88.ui_testing.di

import com.mcmouse88.ui_testing.data.core.network.MoviesHttpClient
import com.mcmouse88.ui_testing.data.core.network.MoviesHttpClientImpl
import com.mcmouse88.ui_testing.data.movies.remote.MoviesApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface NetWorkModule {

    @[Binds Singleton]
    fun bindMovieDbClient(
        impl: MoviesHttpClientImpl
    ): MoviesHttpClient
}

@[Module InstallIn(SingletonComponent::class)]
object ApiWrapperModule {
    @[Provides Singleton]
    fun provideMoviesApi(client: MoviesHttpClient): MoviesApi = client.moviesApi
}