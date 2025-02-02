package com.pratclot.scout24

import com.pratclot.api.CountriesService
import com.pratclot.dto.scout24.CountryDto
import javax.inject.Inject

private const val CONTINENT_EU = "Europe"
private const val MAGIC_LAT_LNG = 20.0

class CountryRepo @Inject constructor(
    private val countriesService: CountriesService,
) {
    suspend fun getCountries(): List<Country> {
        return countriesService.getCountriesNamesFlags()
            .map { it.toDomain() }
    }
}


fun CountryDto.toDomain() = Country(
    name = name.common,
    capital = capital.firstOrNull() ?: "n/a",
    flagURL = flags.png,
    isEU = continents.contains(CONTINENT_EU),
    flagDescription = flags.alt,
    lat = capitalInfo.latlng.firstOrNull() ?: MAGIC_LAT_LNG,
    lng = capitalInfo.latlng.lastOrNull() ?: MAGIC_LAT_LNG,
)