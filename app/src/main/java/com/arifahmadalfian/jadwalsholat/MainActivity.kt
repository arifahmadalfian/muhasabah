package com.arifahmadalfian.jadwalsholat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.arifahmadalfian.jadwalsholat.navigation.BottomNavigationCustom
import com.arifahmadalfian.jadwalsholat.navigation.NavigationGraph
import com.arifahmadalfian.jadwalsholat.presentation.ContentJadwalSholat
import com.arifahmadalfian.jadwalsholat.ui.theme.Gren
import com.arifahmadalfian.jadwalsholat.ui.theme.JadwalSholatTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun App() {
    val color: Color = Gren
    val lazyListState = rememberLazyListState()
    val navController = rememberNavController()

    JadwalSholatTheme(color = color) {
        Scaffold(
            topBar = {
                AppBar(color = color)
            },
            bottomBar = {
                BottomNavigationCustom(navController = navController)
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                NavigationGraph(navController = navController, lazyListState = lazyListState)
            }
        }
    }
}

@Composable
fun AppBar(color: Color) {
    TopAppBar(
        backgroundColor = color
    ) {
        Text("Title")
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JadwalSholatTheme(color = Color.White) {
        Surface {
            ContentJadwalSholat()
        }
    }
}