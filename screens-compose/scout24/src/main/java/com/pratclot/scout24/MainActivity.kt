package com.pratclot.scout24

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.pratclot.scout24.ui.activity.COUNTRY_TAG
import com.pratclot.scout24.ui.activity.SecondScreen
import com.pratclot.scout24.ui.component.CountryItem
import com.pratclot.scout24.ui.theme.GenericAndroidProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val countriesList = MutableStateFlow(listOf<Country>())

    private val navigateToSecondScreen = { country: Country ->
        Intent(this, SecondScreen::class.java).apply {
            putExtra(COUNTRY_TAG, country)
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(this)
        }
    }

    @Inject
    lateinit var getCountriesEU: GetCountriesEU
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            countriesList.update {
                getCountriesEU()
            }
        }

        enableEdgeToEdge()
        setContent {
            GenericAndroidProjectTheme {
                val countries = countriesList.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(
                            items = countries.value,
//                            key = { it.uniqueId() }
                        ) { item ->
                            CountryItem(
                                item = item,
                                onClick = { navigateToSecondScreen(item) }
                            )
                        }
                    }
                }
            }
        }
    }

}