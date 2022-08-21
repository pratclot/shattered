package com.pratclot.di

import com.pratclot.IdlingResourceWrapper
import com.pratclot.IdlingResourceWrapperActual
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class IdlingResourceModule {
    @Binds
    abstract fun bindIdlingResourceProvider(idlingResourceProviderActual: IdlingResourceWrapperActual): IdlingResourceWrapper
}
