package com.pratclot

import com.pratclot.domain.NewsItem

fun createNewsItem(title: CharSequence = "", newsLink: String = "") = NewsItem(
    title = title,
    image_url = "",
    resource_url = "",
    resource_name = "",
    news_link = newsLink
)

fun createListNewsItems(count: Int = 5, title: CharSequence = "", newsLink: String = "") =
    List(count) { createNewsItem(title, newsLink) }
