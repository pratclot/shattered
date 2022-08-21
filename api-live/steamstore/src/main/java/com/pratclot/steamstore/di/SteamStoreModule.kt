package com.pratclot.steamstore.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pratclot.common.di.OKHTTP_CLIENT_LOGGING
import com.pratclot.steamstore.API_HOST_STEAM_STORE
import com.pratclot.steamstore.SteamStore
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
class SteamStoreModule {

    @Provides
    fun provideSteamStore(
        @Named(OKHTTP_CLIENT_LOGGING) okHttpClient: OkHttpClient,
        json: Json,
        contentType: MediaType
    ): SteamStore = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_HOST_STEAM_STORE)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(SteamStore::class.java)
}
