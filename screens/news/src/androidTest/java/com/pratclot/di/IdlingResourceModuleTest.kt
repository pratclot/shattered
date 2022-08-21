package com.pratclot.di

import com.pratclot.IdlingResourceWrapper
import com.pratclot.IdlingResourceWrapperTest
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [IdlingResourceModule::class],
)
@Module
abstract class IdlingResourceModuleTest {

    @Binds
    abstract fun binsIdlingResourceProvider(idlingResourceProviderTest: IdlingResourceWrapperTest): IdlingResourceWrapper
}
