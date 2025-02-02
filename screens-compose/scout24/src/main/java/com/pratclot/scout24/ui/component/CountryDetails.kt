package com.pratclot.scout24.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CountryDetails(
    name: String,
    capital: String,
    flagURL: String,
    flagDescription: String,
) {
    CountryFlag(flagDescription, flagURL)
    Text(name)
    Text(capital)
}
