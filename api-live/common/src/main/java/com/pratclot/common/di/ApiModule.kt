package com.pratclot.common.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

private const val APPLICATION_JSON = "application/json"
const val OKHTTP_CLIENT = "OKHTTP_CLIENT"
const val OKHTTP_CLIENT_LOGGING = "OKHTTP_CLIENT_LOGGING"
const val OKHTTP_CLIENT_BUILDER = "OKHTTP_CLIENT_BUILDER"
const val OKHTTP_CLIENT_BUILDER_LOGGING = "OKHTTP_CLIENT_BUILDER_LOGGING"
const val HTTP_LOGGING_INTERCEPTOR = "HTTP_LOGGING_INTERCEPTOR"

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Reusable
    @Provides
    fun provideJson(): Json = Json {
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    @Reusable
    @Provides
    fun provideContentType(): MediaType = APPLICATION_JSON.toMediaType()

    @Named(OKHTTP_CLIENT_BUILDER_LOGGING)
    @Provides
    fun provideOkHttpClientBuilderLogging(@Named(HTTP_LOGGING_INTERCEPTOR) loggingInterceptor: Interceptor): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)


    @Named(OKHTTP_CLIENT)
    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()

    @Named(OKHTTP_CLIENT_LOGGING)
    @Provides
    fun provideOkHttpClientLogging(
        @Named(OKHTTP_CLIENT_BUILDER_LOGGING) builder: OkHttpClient.Builder,
    ): OkHttpClient = builder
        .build()

    @Named(OKHTTP_CLIENT_BUILDER)
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Named(HTTP_LOGGING_INTERCEPTOR)
    @Provides
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
