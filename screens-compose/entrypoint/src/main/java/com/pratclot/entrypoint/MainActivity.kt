@file:OptIn(ExperimentalMaterial3Api::class)

package com.pratclot.entrypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pratclot.MyNewsActivity
import com.pratclot.entrypoint.ui.theme.GenericAndroidProjectTheme
import com.pratclot.steamstore.SteamStoreActivity
import com.pratclot.themoviedb.MoviesActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenericAndroidProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = EntryPoint.id) {
                        composable(route = EntryPoint.id) {
                            EntryPoint(
                                onChoiceSteamStore = { navController.navigate(SteamStore.id) },
                                onChoiceNews = { navController.navigate(News.id) },
                            ) { navController.navigate(Movies.id) }
                        }
                        activity(route = SteamStore.id) {
                            activityClass = SteamStoreActivity::class
                        }
                        activity(route = News.id) {
                            activityClass = MyNewsActivity::class
                        }
                        activity(route = Movies.id) {
                            activityClass = MoviesActivity::class
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EntryPoint(
    onChoiceSteamStore: () -> Unit,
    onChoiceNews: () -> Unit,
    onChoiceMovies: () -> Unit
) {
    Column(
        Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello there, please choose a screen, thanks!")
        SteamStore.id.let {
            Button(onClick = onChoiceSteamStore) {
                Text(text = it)
            }
        }
        News.id.let {
            Button(onClick = onChoiceNews) {
                Text(text = it)
            }
        }
        Movies.id.let {
            Button(onClick = onChoiceMovies) {
                Text(text = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GenericAndroidProjectTheme {
        EntryPoint({}, {}) { }
    }
}
