package com.arifahmadalfian.muhasabah.presentation.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CheckAnimation(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    isEnable: MutableState<Boolean> = mutableStateOf(false),
    scale: Animatable<Float, AnimationVector1D>,
) {
    val scope = rememberCoroutineScope()

    Icon(
        imageVector = Icons.Outlined.CheckCircle,
        contentDescription = "",
        tint = if (isEnable.value) Color.Green else Color.LightGray,
        modifier = modifier
            .scale(scale = scale.value)
            .size(size = 32.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
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
    )
}