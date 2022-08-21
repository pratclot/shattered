package com.pratclot.api

import com.pratclot.dto.NewsDto

interface NewsApi {

    suspend fun retrieveNews(language: String = "en"): NewsDto
}
