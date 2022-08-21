package com.pratclot.di

import com.pratclot.DispatcherWrapper
import com.pratclot.DispatcherWrapperActual
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DispatchersModule {

    @Binds
    abstract fun bindDispatcherWrapper(wrapperActual: DispatcherWrapperActual): DispatcherWrapper
}
