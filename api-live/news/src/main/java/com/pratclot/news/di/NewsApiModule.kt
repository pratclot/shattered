package com.pratclot.news.di

import com.pratclot.api.NewsApi
import com.pratclot.news.News
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NewsApiModule {

    @Binds
    abstract fun provideNews(news: News): NewsApi
}
