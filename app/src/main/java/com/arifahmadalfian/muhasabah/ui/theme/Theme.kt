package com.arifahmadalfian.muhasabah.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorPalette = lightColors(
    primary = Gren,
    primaryVariant = Gold,
    secondary = Teal200
)

@ExperimentalMaterial3Api
@Composable
fun MuhasabahTheme(appBarAlpha: Float, content: @Composable () -> Unit) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        LaunchedEffect(key1 = appBarAlpha) {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Black.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }

            val windowsInsetsController = WindowCompat.getInsetsController(window, view)
            windowsInsetsController.isAppearanceLightStatusBars = appBarAlpha in -0.0f..0.5f
            windowsInsetsController.isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}