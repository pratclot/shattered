package com.pratclot.steamstore

import com.pratclot.api.SteamStoreApi
import com.pratclot.domain.SteamStoreItemDomain
import com.pratclot.dto.SteamStoreItem
import javax.inject.Inject

class SteamStoreRepo @Inject constructor(
    private val steamStore: SteamStoreApi
) {
    suspend fun storeSearch(term: String) =
        steamStore.storeSearch(term).items.map(::mapToDomainItems)

    private fun mapToDomainItems(steamStoreItem: SteamStoreItem): SteamStoreItemDomain =
        with(steamStoreItem) {
            SteamStoreItemDomain(
                name,
                tiny_image,
            )
        }
}
