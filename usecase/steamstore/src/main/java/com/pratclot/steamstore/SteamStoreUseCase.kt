package com.pratclot.steamstore

import javax.inject.Inject

private const val BRUTAL = "brutal"

class SteamStoreUseCase @Inject constructor(private val steamStoreRepo: SteamStoreRepo) {

    suspend fun getBrutalLegend() = steamStoreRepo.storeSearch(BRUTAL)

    suspend fun getBrutalLegendShuffled() = steamStoreRepo.storeSearch(BRUTAL).shuffled()
}
