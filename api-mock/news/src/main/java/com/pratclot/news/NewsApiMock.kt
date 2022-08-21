package com.pratclot.news

import com.pratclot.api.NewsApi
import com.pratclot.dto.News
import com.pratclot.dto.NewsDto
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import java.nio.charset.Charset
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

const val FILENAME = "raw/currents.json"

class NewsApiMock @Inject constructor() : NewsApi {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun retrieveNews(language: String): NewsDto =
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
                json.decodeFromString<NewsDto>(jsonString).let { list ->
                    continuation.resume(list) { continuation.resumeWithException(it) }
                }
            }
        }
}
