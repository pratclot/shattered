package com.pratclot.themoviedb.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pratclot.steamstore.ImmutableHolder
import com.pratclot.themoviedb.MoviesViewModel
import com.pratclot.themoviedb.design.STANDARD_PADDING

@Composable
fun Greeting(
    navigate: () -> Unit,
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val navigationEvent = viewModel.navigationEvent.collectAsState()
    if (navigationEvent.value) {
        viewModel.navigationComplete()
        navigate()
    }

    val movies by viewModel.movies.collectAsState()
    val imageLoader = ImmutableHolder(viewModel.imageLoader)

    val isRefreshing by viewModel.loadingEvent.collectAsState()
    val refresh = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    SwipeRefresh(
        state = refresh,
        onRefresh = { viewModel.userRefreshed() },
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            content = {
                items(movies) {
                    Card(
                        modifier = Modifier
                            .padding(STANDARD_PADDING)
                            .clickable { viewModel.clicked(it) }
                    ) {
                        Row(Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = it.pictureUrl,
                                contentDescription = "contentDescription",
                                imageLoader = imageLoader.item,
                                modifier = Modifier.width(150.dp)
                            )
                            Title(it)
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }

    val errorEvent = viewModel.errorEvent.collectAsState().value
    AnimatedVisibility(
        visible = errorEvent,
        enter = slideInHorizontally(),
        exit = fadeOut()
    ) {
        ErrorDialog()
    }
}
