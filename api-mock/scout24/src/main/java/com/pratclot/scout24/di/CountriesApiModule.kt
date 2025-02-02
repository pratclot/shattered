package com.pratclot.scout24.di

import com.pratclot.DispatcherWrapper
import com.pratclot.api.CountriesService
import com.pratclot.dto.scout24.CountryDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

private const val FILENAME = "raw/countries.json"

@InstallIn(SingletonComponent::class)
@Module
abstract class CountriesApiModule {

    class CountriesApiMock @Inject constructor(
        private val json: Json,
        private val dispatcherWrapper: DispatcherWrapper,
    ) : CountriesService {

        private suspend inline fun <reified T> readFromFile(path: String): T =
            withContext(dispatcherWrapper.io) {
                suspendCancellableCoroutine { continuation ->
                    val stream = javaClass.classLoader.getResourceAsStream(path)
                    if (stream == null)
                        continuation.resumeWithException(FileNotFoundException("Did not find ${path}"))
                    else stream.run {
                        continuation.invokeOnCancellation { close() }
                        val size = available()
                        val buffer = ByteArray(size)
                        read(buffer)
                        close()
                        val jsonString = buffer.toString(Charset.defaultCharset())
                        json.decodeFromString<T>(jsonString).let { list ->
                            continuation.resume(list) { continuation.resumeWithException(it) }
                        }
                    }
                }
            }

        override suspend fun getCountries(): List<CountryDto> = readFromFile(FILENAME)
        override suspend fun getCountriesNamesFlags(): List<CountryDto> = readFromFile(FILENAME)

    }

    @Binds
    abstract fun bindCountriesApi(CountriesApiMock: CountriesApiMock): CountriesService
}
