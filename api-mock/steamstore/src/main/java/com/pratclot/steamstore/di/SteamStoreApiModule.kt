package com.pratclot.steamstore.di

import com.pratclot.api.SteamStoreApi
import com.pratclot.dto.NewsDto
import com.pratclot.dto.SteamStoreSearchResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

private const val FILENAME = "raw/steamstore.json"

@InstallIn(SingletonComponent::class)
@Module
abstract class SteamStoreApiModule {

    class SteamStoreApiMock @Inject constructor(private val json: Json) : SteamStoreApi {
        override suspend fun storeSearch(
            term: String,
            l: String,
            cc: String
        ): SteamStoreSearchResponse =
            suspendCancellableCoroutine { continuation ->
                val stream = javaClass.classLoader.getResourceAsStream(FILENAME)
                if (stream == null)
                    continuation.resumeWithException(FileNotFoundException("Did not find $FILENAME"))
                else stream.run {
                    continuation.invokeOnCancellation { close() }
                    val size = available()
                    val buffer = ByteArray(size)
                    read(buffer)
                    close()
                    val jsonString = buffer.toString(Charset.defaultCharset())
                    json.decodeFromString<SteamStoreSearchResponse>(jsonString).let { list ->
                        continuation.resume(list) { continuation.resumeWithException(it) }
                    }
                }
            }

    }

    @Binds
    abstract fun bindSteamStoreApi(steamStore: SteamStoreApiModule.SteamStoreApiMock): SteamStoreApi
}
