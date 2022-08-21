package com.pratclot.usecase

import com.pratclot.domain.NewsItem
import com.pratclot.news.NewsRepo
import javax.inject.Inject

class NewsUseCaseActual @Inject constructor(
    private val newsRepo: NewsRepo
) : NewsUseCase {
    override suspend fun retrieveNews(): List<NewsItem> = newsRepo.retrieveNewsWithDelay()
}
