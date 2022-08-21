package com.pratclot.news

import com.pratclot.api.NewsApi
import com.pratclot.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

const val API_HOST_NEWS = "https://api.currentsapi.services/"


interface News : NewsApi {

    @GET("v1/latest-news")
    override suspend fun retrieveNews(
        @Query("language") language: String
    ): NewsDto
}
