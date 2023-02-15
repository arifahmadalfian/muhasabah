package com.arifahmadalfian.muhasabah.presentation.quran

import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.arifahmadalfian.muhasabah.ui.theme.Gren

@ExperimentalMaterial3Api
@Composable
fun AppBar(appBarAlpha: Float) {
    CenterAlignedTopAppBar(
        modifier = Modifier.alpha(appBarAlpha),
        title = { Text("Title") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Gren,
            scrolledContainerColor = Gren,
        )
    )
}