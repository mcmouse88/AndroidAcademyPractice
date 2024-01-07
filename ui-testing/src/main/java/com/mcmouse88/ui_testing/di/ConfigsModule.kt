package com.mcmouse88.ui_testing.di

import com.mcmouse88.ui_testing.BuildConfig
import com.mcmouse88.ui_testing.data.core.MovieUrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ConfigsModule {

    @[Provides Singleton]
    fun provideMovieUrlProvider(): MovieUrlProvider {
        return MovieUrlProvider(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.THE_MOVIE_DB_API_KEY,
            baseImageUrl = BuildConfig.BASE_IMAGE_URL,
            browseMovieBaseUrl = BuildConfig.BROWSE_MOVIE_BASE_URL
        )
    }
}