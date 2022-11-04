package com.pratclot.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pratclot.themoviedb.composables.Greeting
import com.pratclot.themoviedb.composables.Movie
import com.pratclot.themoviedb.navigation.EntryPoint
import com.pratclot.themoviedb.navigation.MovieScreen
import com.pratclot.themoviedb.ui.theme.GenericAndroidProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GenericAndroidProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    /**
                     * Here is the explanation:
                     * https://pratclot.com/article/jetpack-compose-navigation-handling-of-hilt-view-model
                     */
                    val viewModel: MoviesViewModel = hiltViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = EntryPoint.id
                    ) {
                        composable(route = EntryPoint.id) {
                            Greeting({ navController.navigate(MovieScreen.id) }, viewModel)
                        }
                        composable(route = MovieScreen.id) {
                            Movie(viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GenericAndroidProjectTheme {
        Greeting({})
    }
}
