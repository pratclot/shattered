package com.pratclot.news

import com.pratclot.api.NewsApi
import com.pratclot.domain.NewsItem
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsApi: NewsApi,
) {
    suspend fun retrieveNewsWithDelay() = newsApi.retrieveNews().news
        .map {
            NewsItem(
                title = it.title,
                image_url = it.image,
                resource_url = it.url,
                resource_name = it.description,
                news_link = it.url,
            )
        }
}
