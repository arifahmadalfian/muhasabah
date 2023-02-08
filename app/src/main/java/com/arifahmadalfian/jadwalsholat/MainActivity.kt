package com.arifahmadalfian.jadwalsholat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.arifahmadalfian.jadwalsholat.presentation.ContentJadwalSholat
import com.arifahmadalfian.jadwalsholat.ui.theme.JadwalSholatTheme
import com.arifahmadalfian.jadwalsholat.ui.theme.TabColorOne
import com.arifahmadalfian.jadwalsholat.view.TabScreenOne
import com.arifahmadalfian.jadwalsholat.view.TabScreenTwo
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JadwalSholatTheme {
                Surface {
                    ContentJadwalSholat()
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}