package com.pratclot.dto.scout24

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val name: Name,
    val continents: List<String>,
    val capital: List<String>,
    val flags: Flags,
    val capitalInfo: CapitalInfo,
) {
    @Serializable
    data class Name(val common: String)

    @Serializable
    data class Flags(
        val png: String,
        val alt: String,
    )

    @Serializable
    data class CapitalInfo(
        val latlng: List<Double>
    )
}