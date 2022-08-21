package com.pratclot.di

import com.pratclot.common.secrets.SecretsProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SecretsModule {

    @Reusable
    @Binds
    abstract fun bindSecretProvider(secretsProviderActual: SecretsProviderActual): SecretsProvider
}
