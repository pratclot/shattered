package com.pratclot.usecase.di

import com.pratclot.usecase.NewsUseCase
import com.pratclot.usecase.NewsUseCaseActual
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindUseCase(useCase: NewsUseCaseActual): NewsUseCase
}
