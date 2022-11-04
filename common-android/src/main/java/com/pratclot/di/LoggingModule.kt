package com.pratclot.di

import com.pratclot.themoviedb.LoggingWrapper
import com.pratclot.themoviedb.LoggingWrapperLive
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LoggingModule {
    @Binds
    abstract fun bindLoggingWrapper(logger: LoggingWrapperLive): LoggingWrapper
}
