package com.pratclot.scout24

import com.pratclot.api.CountriesService
import com.pratclot.dto.scout24.CountryDto
import retrofit2.http.GET
import retrofit2.http.Headers

const val API_HOST_SCOUT_24 = "https://restcountries.com"

interface CountriesServiceLive: CountriesService {

    @Headers("Content-Type: application/json")
    @GET("v3.1/all")
    override suspend fun getCountries(): List<CountryDto>

    @Headers("Content-Type: application/json")
    @GET("v3.1/all?fields=name,flags,continents,capital,capitalInfo")
    override suspend fun getCountriesNamesFlags(): List<CountryDto>

//    companion object {
//        fun provide(): CountriesService {
//            val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl(API_HOST_SCOUT_24)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            return retrofit.create(CountriesService::class.java)
//        }
//    }
}
