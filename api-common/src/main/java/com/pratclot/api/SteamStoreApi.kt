package com.pratclot.api

import com.pratclot.dto.SteamStoreSearchResponse

interface SteamStoreApi {

    suspend fun storeSearch(
        term: String,
        l: String = "english",
        cc: String = "US",
    ): SteamStoreSearchResponse
}
