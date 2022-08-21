package com.pratclot.dto

import kotlinx.serialization.Serializable

@Serializable
data class SteamStoreSearchResponse(
    val total: Int,
    val items: List<SteamStoreItem>,
)
