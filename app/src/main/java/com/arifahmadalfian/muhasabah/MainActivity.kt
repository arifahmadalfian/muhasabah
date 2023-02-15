package com.arifahmadalfian.muhasabah

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.arifahmadalfian.muhasabah.navigation.BottomNavigationCustom
import com.arifahmadalfian.muhasabah.navigation.NavigationGraph
import com.arifahmadalfian.muhasabah.presentation.sholat.ContentJadwalSholat
import com.arifahmadalfian.muhasabah.ui.theme.MuhasabahTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreen
        }
        setContent {
            App {
                keepSplashScreen = false
            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun App(onDataLoad: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val contentOffset = scrollBehavior.state.contentOffset * -1

    var fakeLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        fakeLoading = false
        onDataLoad()
    }

    val appBarAlpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = contentOffset) {
        if (contentOffset < 0.1f) {
            scope.launch {
                appBarAlpha.animateTo(
                    0f,
                    animationSpec = tween(100),
                )
            }
        }
        if (contentOffset in 0.1f..200f) {
            scope.launch {
                appBarAlpha.animateTo(
                    contentOffset / 100f,
                    animationSpec = tween(100),
                )
            }
        }
    }

    Log.d("alpha", "${appBarAlpha.value} & $contentOffset")

    if (!fakeLoading) {
        MuhasabahTheme(appBarAlpha = appBarAlpha.value) {
            Scaffold(
                modifier = Modifier.navigationBarsPadding(),
                bottomBar = {
                    BottomNavigationCustom(navController = navController)
                }
            ) { paddingValues ->
                Surface(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    NavigationGraph(
                        navController = navController,
                        scrollBehavior = scrollBehavior,
                        appBarAlpha = appBarAlpha.value
                    )
                }
            }
        }
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MuhasabahTheme(1f) {
        Surface {
            ContentJadwalSholat()
        }
    }
}