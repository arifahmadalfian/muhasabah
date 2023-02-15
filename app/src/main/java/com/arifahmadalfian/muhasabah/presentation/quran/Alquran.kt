package com.arifahmadalfian.muhasabah.presentation.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun AlquranScreen(scrollBehavior: TopAppBarScrollBehavior, appBarAlpha: Float) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Transparent))
            }
            items(count = 100) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Hello Jetpack Compose"
                )

            }
        }
        AppBar(appBarAlpha = appBarAlpha)
    }

}