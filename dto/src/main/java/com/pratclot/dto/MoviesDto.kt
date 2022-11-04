package com.pratclot.dto

@kotlinx.serialization.Serializable
data class MoviesDto(
    val page: Int,
    val results: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
)
