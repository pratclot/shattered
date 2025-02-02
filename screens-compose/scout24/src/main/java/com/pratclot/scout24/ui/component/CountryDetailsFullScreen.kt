package com.pratclot.scout24.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.pratclot.scout24.ui.theme.Typography

@Preview
@Composable
private fun CountryDetailsFullScreenPreview() = CountryDetailsFullScreen(
    name = "Germany",
    capital = "Dresden",
    flagURL = "none",
    flagDescription = "none",
    lat = 51.0,
    lng = 13.75,
    modifier = Modifier.background(Color.White)
)

@Composable
fun CountryDetailsFullScreen(
    name: String,
    capital: String,
    flagURL: String,
    flagDescription: String,
    lat: Double,
    lng: Double,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        CountryFlag(
            flagDescription,
            flagURL,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
        Text(
            text = name,
            style = Typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = capital,
            style = Typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        val capitalLocation = LatLng(lat, lng)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(capitalLocation, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        )
    }
}