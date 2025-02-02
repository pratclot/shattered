package com.pratclot.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val page: Int,
    val results: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
)
