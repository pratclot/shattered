package com.pratclot.news.di

import com.pratclot.api.NewsApi
import com.pratclot.news.NewsApiMock
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NewsApiMockModule {

    @Binds
    abstract fun provideNews(newsApiMock: NewsApiMock): NewsApi
}
