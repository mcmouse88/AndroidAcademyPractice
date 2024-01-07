package com.mcmouse88.ui_testing.data.core.network

import com.mcmouse88.ui_testing.data.core.MovieUrlProvider
import com.mcmouse88.ui_testing.data.movies.remote.MoviesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

interface MoviesHttpClient {
    val moviesApi: MoviesApi
}

class MoviesHttpClientImpl @Inject constructor(
    movieUrlProvider: MovieUrlProvider
): MoviesHttpClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor(QueryInterceptor(hashMapOf("api_key" to movieUrlProvider.apiKey)))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(movieUrlProvider.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override val moviesApi: MoviesApi by lazy(LazyThreadSafetyMode.NONE) {
        retrofit.create()
    }
}