package com.pratclot.scout24.ui.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pratclot.scout24.Country
import com.pratclot.scout24.R
import com.pratclot.scout24.ui.component.CountryDetailsFullScreen
import com.pratclot.scout24.ui.theme.GenericAndroidProjectTheme

const val COUNTRY_TAG = "COUNTRY_TAG"

class SecondScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val country: Country? = intent.getSerializableExtra(COUNTRY_TAG) as? Country

        /**
         * TODO A bit of copypasta here, sorry!
         */
        enableEdgeToEdge()
        setContent {
            GenericAndroidProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(8.dp)
                    ) {
                        country?.let {
                            CountryDetailsFullScreen(
                                name = it.name,
                                capital = it.capital,
                                flagURL = it.flagURL,
                                flagDescription = it.flagDescription,
                                lat = it.lat,
                                lng = it.lng
                            )
                        } ?: Text(stringResource(R.string.nothing_to_show_please_go_back))
                    }
                }
            }
        }
    }
}
