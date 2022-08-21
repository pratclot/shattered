package com.pratclot.usecase

import com.pratclot.domain.NewsItem

interface NewsUseCase {

    suspend fun retrieveNews(): List<NewsItem>
}
