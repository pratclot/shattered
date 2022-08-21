package com.pratclot.dto

import kotlinx.serialization.Serializable

@Serializable
data class SteamStoreItem(
    val controller_support: String?,
    val id: Int,
    val metascore: String,
    val name: String,
    val platforms: Platforms,
    val price: Price?,
    val streamingvideo: Boolean,
    val tiny_image: String,
    val type: String
)

@Serializable
data class Platforms(
    val linux: Boolean,
    val mac: Boolean,
    val windows: Boolean
)

@Serializable
data class Price(
    val currency: String,
    val `final`: Int,
    val initial: Int
)
