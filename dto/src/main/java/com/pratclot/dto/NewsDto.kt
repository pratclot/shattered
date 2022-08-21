package com.pratclot.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    val news: List<News>
)
