package com.arifahmadalfian.muhasabah.presentation.sholat

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.arifahmadalfian.muhasabah.ui.theme.Gold
import com.arifahmadalfian.muhasabah.ui.theme.Gren
import com.arifahmadalfian.muhasabah.ui.theme.Gren50
import com.arifahmadalfian.muhasabah.presentation.view.CardDay
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ContentJadwalSholat(
    modifier: Modifier = Modifier
) {
    val tabItems = listOf("Wajib", "Sunah")
    val pagerState = rememberPagerState(pageCount = tabItems.size)
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.background(Gren50)) {
        TabScreen(pagerState = pagerState, tabItems = tabItems, scope = scope)
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun TabScreen(pagerState: PagerState, tabItems: List<String>, scope: CoroutineScope) {
    Column{
        Tabs(pagerState = pagerState, tabItems = tabItems, scope = scope)
        TabsContent(pagerState = pagerState, tabItems = tabItems)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, tabItems: List<String>, scope: CoroutineScope) {

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Gren,
        modifier = Modifier
            .padding(all = 6.dp)
            .clip(RoundedCornerShape(10.dp)),
        indicator = indicator
    ) {
        tabItems.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        text = title,
                        style = if (pagerState.currentPage == index) TextStyle(
                            color = Color.White,
                            fontSize = 18.sp
                        ) else TextStyle(
                            color = Gold,
                            fontSize = 16.sp
                        )
                    )
                },
                selected = pagerState.currentPage == index,
                modifier = Modifier
                    .zIndex(6f)
                    .padding(2.dp),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalAnimationApi
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
            CardDay()
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 900f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 900f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .padding(6.dp)
            .fillMaxSize()
            .background(color = Gold.copy(alpha = 0.5f), RoundedCornerShape(15))
            .zIndex(1f)
    )
}
