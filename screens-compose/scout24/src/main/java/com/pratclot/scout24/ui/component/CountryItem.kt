package com.pratclot.scout24.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pratclot.scout24.Country

@Composable
fun CountryItem(
    item: Country,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable { onClick() }
) {
    CountryDetails(
        name = item.name,
        capital = item.capital,
        flagURL = item.flagURL,
        flagDescription = item.flagDescription
    )
}


@Preview
@Composable
private fun CountryItemPreview() = CountryItem(
    Country(
        name = "Germany",
        capital = "Dresden",
        flagURL = "none",
        isEU = true,
        flagDescription = "n/a",
        lat = 0.0,
        lng = 0.0,
    ),
    onClick = {},
    modifier = Modifier.background(Color.White)
)