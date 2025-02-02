package com.pratclot.scout24.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pratclot.api.CountriesService
import com.pratclot.common.di.OKHTTP_CLIENT_LOGGING
import com.pratclot.scout24.API_HOST_SCOUT_24
import com.pratclot.scout24.CountriesServiceLive
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class Scout24Module {

    @Provides
    fun provideScout24(
        @Named(OKHTTP_CLIENT_LOGGING) okHttpClient: OkHttpClient,
        json: Json,
        contentType: MediaType
    ): CountriesService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_HOST_SCOUT_24)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(CountriesServiceLive::class.java)
}
