package com.pratclot.themoviedb.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pratclot.common.di.OKHTTP_CLIENT_BUILDER_LOGGING
import com.pratclot.common.secrets.SecretKeys
import com.pratclot.common.secrets.SecretsProvider
import com.pratclot.themoviedb.API_HOST_MOVIES
import com.pratclot.themoviedb.MoviesApiLive
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

private const val MOVIES_AUTH = "MOVIES_AUTH"

@InstallIn(SingletonComponent::class)
@Module
class MoviesModule {

    @Provides
    fun provideMovies(
        @Named(MOVIES_AUTH) okHttpClient: OkHttpClient,
        json: Json,
        contentType: MediaType
    ): MoviesApiLive = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_HOST_MOVIES)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(MoviesApiLive::class.java)

    @Named(MOVIES_AUTH)
    @Provides
    fun provideOkHttpClient(
        @Named(OKHTTP_CLIENT_BUILDER_LOGGING) okHttpClientBuilder: OkHttpClient.Builder,
        @Named(MOVIES_AUTH) authInterceptor: Interceptor,
    ): OkHttpClient = okHttpClientBuilder
        .addInterceptor(authInterceptor)
        .build()

    @Named(MOVIES_AUTH)
    @Provides
    fun provideApiKeyInterceptor(secretsProvider: SecretsProvider): Interceptor =
        Interceptor { chain ->
            with(chain) {
                request().run {
                    val newUrl = url.newBuilder().addQueryParameter(
                        "api_key",
                        secretsProvider.getSecretString(SecretKeys.API_MOVIES)
                    ).build()
                    newBuilder()
                        .url(newUrl)
                        .build()
                }.let {
                    proceed(it)
                }
            }
        }

}
