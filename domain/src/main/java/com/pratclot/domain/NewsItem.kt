package com.pratclot.domain

data class NewsItem(
    val title: CharSequence,
    val image_url: String,
    val resource_url: String,
    val resource_name: CharSequence,
    val news_link: String,
)
