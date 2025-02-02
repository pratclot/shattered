package com.pratclot.scout24

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val capital: String,
    val flagURL: String,
    val isEU: Boolean,
    val flagDescription: String,
    val lat: Double,
    val lng: Double,
): java.io.Serializable

fun Country.uniqueId() = name + capital + flagURL + isEU + flagDescription

