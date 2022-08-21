package com.pratclot

import java.io.InputStream

interface AssetProvider {
    suspend fun open(path: String): InputStream
}
