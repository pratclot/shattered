package com.pratclot.themoviedb

import com.pratclot.DispatcherWrapper
import com.pratclot.api.MoviesApi
import com.pratclot.dto.MovieDto
import com.pratclot.dto.MoviesDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

private const val FILENAME = "raw/themoviesdb.json"
private const val FILENAME_ONE_MOVIE = "raw/onemovie.json"

@InstallIn(SingletonComponent::class)
@Module
abstract class MoviesApiModule {

    class MoviesApiMock @Inject constructor(
        private val json: Json,
        private val dispatcherWrapper: DispatcherWrapper,
    ) : MoviesApi {

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

        override suspend fun getMovies(): MoviesDto = readFromFile(FILENAME)

        override suspend fun getMovieById(id: Int): MovieDto = readFromFile(FILENAME_ONE_MOVIE)

    }

    @Binds
    abstract fun bindMoviesApi(moviesApiMock: MoviesApiMock): MoviesApi
}
