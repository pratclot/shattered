package com.pratclot.scout24

import javax.inject.Inject

class GetCountries @Inject constructor(
    private val countryRepo: CountryRepo,
) {
    suspend operator fun invoke(): List<Country> {
        return countryRepo.getCountries()
    }

}