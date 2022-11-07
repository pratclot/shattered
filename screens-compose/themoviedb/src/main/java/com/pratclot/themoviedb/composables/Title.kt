package com.pratclot.themoviedb.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pratclot.themoviedb.design.STANDARD_PADDING
import com.pratclot.themoviedb.model.MovieUI

@Composable
fun Title(movieUI: MovieUI) {
    Column(modifier = Modifier.padding(STANDARD_PADDING)) {
        Text(text = movieUI.title, modifier = Modifier.fillMaxWidth())
        Text(text = movieUI.year, modifier = Modifier.fillMaxWidth())
    }
}
