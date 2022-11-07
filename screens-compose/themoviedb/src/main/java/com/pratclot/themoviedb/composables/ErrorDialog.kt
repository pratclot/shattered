package com.pratclot.themoviedb.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog() {
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .wrapContentSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Error!",
                color = Color.Red,
                fontSize = 32.sp,
            )
        }
    }
}
