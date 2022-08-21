package com.pratclot.steamstore.di

import com.pratclot.api.SteamStoreApi
import com.pratclot.dto.SteamStoreSearchResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
abstract class SteamStoreApiModule {

    class SteamStoreApiMock @Inject constructor() : SteamStoreApi {
        override suspend fun storeSearch(
            term: String,
            l: String,
            cc: String
        ): SteamStoreSearchResponse {
            TODO("Not yet implemented")
        }

    }

    @Binds
    abstract fun bindSteamStoreApi(steamStore: SteamStoreApiMock): SteamStoreApi
}
