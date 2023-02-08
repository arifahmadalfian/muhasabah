package com.arifahmadalfian.jadwalsholat.presentation

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arifahmadalfian.jadwalsholat.ui.theme.Denim
import com.arifahmadalfian.jadwalsholat.ui.theme.Malibu
import com.arifahmadalfian.jadwalsholat.ui.theme.TabColorOne
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun ContentJadwalSholat(
    modifier: Modifier = Modifier
) {
    val tabItems = listOf("Wajib", "Sunah")
    val pagerState = rememberPagerState(pageCount = tabItems.size)
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Toolbar()
        TabScreen(pagerState = pagerState, tabItems = tabItems, scope = scope)
    }
}

@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Jadwal Sholat", color = Color.White) },
        backgroundColor = TabColorOne,
    )
}

@ExperimentalPagerApi
@Composable
fun TabScreen(pagerState: PagerState, tabItems: List<String>, scope: CoroutineScope) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState, tabItems = tabItems, scope = scope)
        TabsContent(pagerState = pagerState, tabItems = tabItems)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, tabItems: List<String>, scope: CoroutineScope) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Malibu,
        modifier = Modifier
            .padding(all = 6.dp)
            .background(color = Color.Transparent)
            .clip(RoundedCornerShape(10.dp)),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .width(0.dp)
                    .height(0.dp)
            )
        }
    ) {
        tabItems.forEachIndexed { index, title ->
            val color = remember {
                Animatable(Denim)
            }

            LaunchedEffect(key1 = pagerState.currentPage == index) {
                color.animateTo(
                    if (pagerState.currentPage == index) Color.White else Malibu
                )
            }

            Tab(
                text = {
                    Text(
                        text = title,
                        style = if (pagerState.currentPage == index) TextStyle(
                            color = Denim,
                            fontSize = 18.sp
                        ) else TextStyle(
                            color = Denim,
                            fontSize = 16.sp
                        )
                    )
                },
                selected = pagerState.currentPage == index,
                modifier = Modifier
                    .padding(6.dp)
                    .background(
                        color = color.value,
                        shape = RoundedCornerShape(10.dp)
                    ),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    tabItems: List<String>
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = tabItems[page],
                color = Color.Black
            )
        }
    }
}

//
//@ExperimentalPagerApi
//@Preview(showBackground = true)
//@Composable
//fun JadwalSholatPreview() {
//    JadwalSholatTheme {
//        Surface {
//            ContentJadwalSholat()
//        }
//    }
//}