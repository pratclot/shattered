package com.pratclot.steamstore

import com.pratclot.api.SteamStoreApi
import com.pratclot.dto.SteamStoreSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_HOST_STEAM_STORE = "https://store.steampowered.com/"

interface SteamStore: SteamStoreApi {

    @GET("api/storesearch")
    override suspend fun storeSearch(
        @Query("term") term: String,
        @Query("l") l: String,
        @Query("cc") cc: String,
    ): SteamStoreSearchResponse
}
