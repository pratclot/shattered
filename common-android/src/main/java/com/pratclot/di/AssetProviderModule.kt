package com.pratclot.di

import com.pratclot.AssetProvider
import com.pratclot.AssetProviderActual
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AssetProviderModule {

    @Binds
    abstract fun bindAssetProvider(assetProviderActual: AssetProviderActual): AssetProvider
}
