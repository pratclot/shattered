package com.pratclot.themoviedb.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pratclot.themoviedb.MoviesViewModel
import com.pratclot.themoviedb.design.STANDARD_PADDING

@Composable
fun Movie(viewModel: MoviesViewModel = hiltViewModel()) {
    val selected = viewModel.selected
    if (selected != null) {
        viewModel.run {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = selected.pictureUrl,
                    contentDescription = "contentDescription",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Title(selected)
                Text(text = selected.overview, modifier = Modifier.padding(STANDARD_PADDING))
            }
        }
    }
}
