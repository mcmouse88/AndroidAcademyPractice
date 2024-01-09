package com.mcmouse88.ui_testing.di

import com.mcmouse88.ui_testing.data.review.local.MyReviewsLocalDataSource
import com.mcmouse88.ui_testing.data.review.local.MyReviewsLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface MyReviewModule {

    @[Binds Singleton]
    fun bindMyReviewLocalDataSource(
        impl: MyReviewsLocalDataSourceImpl
    ): MyReviewsLocalDataSource
}