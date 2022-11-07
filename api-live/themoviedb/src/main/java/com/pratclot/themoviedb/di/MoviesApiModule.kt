package com.pratclot.themoviedb.di

import com.pratclot.api.MoviesApi
import com.pratclot.themoviedb.MoviesApiLive
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MoviesApiModule {

    @Binds
    abstract fun provideNews(news: MoviesApiLive): MoviesApi
}
