package com.arifahmadalfian.jadwalsholat.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arifahmadalfian.jadwalsholat.R
import com.arifahmadalfian.jadwalsholat.presentation.ContentJadwalSholat
import com.arifahmadalfian.jadwalsholat.ui.theme.Gold
import com.arifahmadalfian.jadwalsholat.ui.theme.Gren
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var route: String
) {
    object AlQuran : BottomNavItem("Al-Qur'an", R.drawable.quran, "al_quran")
    object JadwalSholat : BottomNavItem("Sholat", R.drawable.clock, "sholat")
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavigationGraph(navController: NavHostController, lazyListState: LazyListState) {
    NavHost(navController, startDestination = BottomNavItem.AlQuran.route) {
        composable(BottomNavItem.AlQuran.route) {
            HomeScreen(lazyListState = lazyListState)
        }
        composable(BottomNavItem.JadwalSholat.route) {
            JobScreen()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun BottomNavigationCustom(navController: NavController) {
    val items = listOf(
        BottomNavItem.AlQuran,
        BottomNavItem.JadwalSholat
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(Gren)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            CustomBottomNavigationItem(item = item, isSelected = currentRoute == item.route) {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}

@Composable
fun HomeScreen(lazyListState: LazyListState) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = 100) {
            Text("Hello Jetpack Compose")
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun JobScreen() {
    ContentJadwalSholat()
}

@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(item: BottomNavItem, isSelected: Boolean, onClick: () -> Unit) {

    val background =
        if (isSelected) Gold.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) Gold else Gold.copy(alpha = 0.5f)

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = item.icon),
                contentDescription = null,
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    color = contentColor
                )
            }
        }
    }
}
