package com.pratclot.steamstore.di

import com.pratclot.api.SteamStoreApi
import com.pratclot.steamstore.SteamStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SteamStoreApiModule {
    @Binds
    abstract fun bindSteamStoreApi(steamStore: SteamStore): SteamStoreApi
}
