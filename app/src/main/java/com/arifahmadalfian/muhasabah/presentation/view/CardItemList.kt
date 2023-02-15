package com.arifahmadalfian.muhasabah.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arifahmadalfian.muhasabah.ui.theme.Gold
import com.arifahmadalfian.muhasabah.ui.theme.Gray
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun CardListItem(
    modifier: Modifier = Modifier
) {

    val isEnable = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isEnable.value) Gold else Gray)
            .clickable {
                isEnable.value = !isEnable.value
                scope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100),
                    )
                }
            }
    ) {
        Row(
            modifier = modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                        ) {
                            append("Subuh")
                        }
                    }
                )
            }

            AnimatedCounter()

            Spacer(modifier = Modifier.padding(6.dp))

            Text(
                text = "11:00",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            )
            
            Spacer(modifier = Modifier.padding(6.dp))

            CheckAnimation(isEnable = isEnable, scale = scale)
        }
    }
}