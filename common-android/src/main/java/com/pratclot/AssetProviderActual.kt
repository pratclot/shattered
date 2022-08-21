package com.pratclot

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

class AssetProviderActual @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcherWrapper: DispatcherWrapper,
) : AssetProvider {

    override suspend fun open(path: String): InputStream = withContext(dispatcherWrapper.io) {
        context.assets.open(path)
    }
}
