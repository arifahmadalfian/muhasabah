package com.arifahmadalfian.muhasabah.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arifahmadalfian.muhasabah.ui.theme.Gren

@ExperimentalAnimationApi
@Composable
fun CardDay() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(Gren),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            repeat(5) {
                CardListItem()
                Divider(
                    modifier = Modifier
                        .background(Gren)
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                )
            }
        }
    }
}