package com.pratclot.scout24

import javax.inject.Inject


class GetCountriesEU @Inject constructor(
    private val getCountries: GetCountries,
) {
    suspend operator fun invoke(): List<Country> {
        return getCountries()
            .filter { it.isEU }
    }

}