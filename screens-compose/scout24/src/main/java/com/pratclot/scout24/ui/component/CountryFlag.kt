package com.pratclot.scout24.ui.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.Coil.imageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pratclot.scout24.R

@Composable
fun CountryFlag(
    flagDescription: String,
    flagURL: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        contentDescription = flagDescription,
        model = ImageRequest.Builder(LocalContext.current)
            .data(flagURL)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.baseline_broken_image_24),
        contentScale = ContentScale.FillHeight,
        imageLoader = imageLoader(LocalContext.current),
        modifier = modifier
            .width(32.dp)
            .fillMaxHeight()
    )
}