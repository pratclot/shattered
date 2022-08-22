package com.pratclot.api.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
class CommonApiModule {

    @Reusable
    @Provides
    fun provideJson(): Json = Json {
        explicitNulls = false
        ignoreUnknownKeys = true
    }
}
