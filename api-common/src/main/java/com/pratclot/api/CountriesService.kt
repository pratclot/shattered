package com.pratclot.api

import com.pratclot.dto.scout24.CountryDto


interface CountriesService {

    suspend fun getCountries(): List<CountryDto>

    suspend fun getCountriesNamesFlags(): List<CountryDto>

}