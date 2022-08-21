package com.pratclot.news.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pratclot.common.di.OKHTTP_CLIENT_BUILDER_LOGGING
import com.pratclot.common.secrets.SecretKeys
import com.pratclot.common.secrets.SecretsProvider
import com.pratclot.dto.NewsDto
import com.pratclot.news.API_HOST_NEWS
import com.pratclot.news.News
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

private const val NEWS_AUTH = "NEWS_AUTH"

@InstallIn(SingletonComponent::class)
@Module
class NewsModule {

    @Provides
    fun provideNews(
        @Named(NEWS_AUTH) okHttpClient: OkHttpClient,
        json: Json,
        contentType: MediaType
    ): News = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_HOST_NEWS)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(News::class.java)

    @Named(NEWS_AUTH)
    @Provides
    fun provideOkHttpClient(
        @Named(OKHTTP_CLIENT_BUILDER_LOGGING) okHttpClientBuilder: OkHttpClient.Builder,
        @Named(NEWS_AUTH) authInterceptor: Interceptor,
    ): OkHttpClient = okHttpClientBuilder
        .addInterceptor(authInterceptor)
        .build()

    @Named(NEWS_AUTH)
    @Provides
    fun provideApiKeyInterceptor(secretsProvider: SecretsProvider): Interceptor =
        Interceptor { chain ->
            with(chain) {
                request().run {
                    newBuilder()
                        .header(
                            "Authorization",
                            secretsProvider.getSecretString(SecretKeys.API_SECRET_NEWS)
                        )
                        .build()
                }.let {
                    proceed(it)
                }
            }
        }

}
