package com.pratclot.steamstore

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * Taken from https://chris.banes.dev/composable-metrics/
 */

@Stable
class StableHolder<T>(val item: T) {
    operator fun component1(): T = item
}

@Immutable
class ImmutableHolder<T>(val item: T) {
    operator fun component1(): T = item
}
