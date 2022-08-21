@file:OptIn(ExperimentalFoundationApi::class)

package com.pratclot.steamstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pratclot.common_compose.LogCompositions
import com.pratclot.steamstore.data.SteamStoreItemUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SteamStoreActivity : ComponentActivity() {

    private val viewModel by viewModels<SteamStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            viewModel.run {
                val swipeRefreshStateActual by loadingState.collectAsState()

                SteamStoreTheme(
                    swipeRefreshStateActual = swipeRefreshStateActual,
                    onRefresh = { refreshRequested() },
                    {
                        ObserveStoreItems(
                            items.observeAsState(emptyList()).value,
                            ImmutableHolder(imageLoader),
                        )
                    },
                )
            }
        }
    }
}

@NonRestartableComposable
@Composable
fun SteamStoreTheme(
    swipeRefreshStateActual: Boolean,
    onRefresh: () -> Unit,
    vararg functions: @Composable () -> Unit,
) = MaterialTheme {
    val swipeRefreshState = rememberSwipeRefreshState(swipeRefreshStateActual)

    LogCompositions(tag = "SteamStoreTheme", msg = "Recomposed theme!")

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
        Modifier.recomposeHighlighter(),
    ) {
        functions.forEach { it() }
    }
}

@Composable
fun ObserveStoreItems(items: List<SteamStoreItemUi>, imageLoader: ImmutableHolder<ImageLoader>) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(items) {
        lazyListState.animateScrollToItem(0)
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .padding(4.dp)
            .recomposeHighlighter()
    ) {
        items(items, key = { it.name }) {
            SteamStoreCard(it, imageLoader, Modifier.animateItemPlacement())
        }
    }
}

@Composable
fun SteamStoreCard(
    steamStoreItemUi: SteamStoreItemUi,
    imageLoader: ImmutableHolder<ImageLoader>,
    modifier: Modifier,
) {
    Card(
        modifier
            .padding(4.dp)
            .height(200.dp)
            .recomposeHighlighter()
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = steamStoreItemUi.img,
                    contentDescription = "contentDescription",
                    imageLoader = imageLoader.item,
                    Modifier.fillMaxWidth(),
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = steamStoreItemUi.name,
                    Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
