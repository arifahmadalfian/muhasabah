package com.arifahmadalfian.muhasabah.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arifahmadalfian.muhasabah.presentation.sholat.ContentJadwalSholat
import com.arifahmadalfian.muhasabah.ui.theme.Gold
import com.arifahmadalfian.muhasabah.ui.theme.Gren
import com.arifahmadalfian.muhasabah.R
import com.arifahmadalfian.muhasabah.presentation.quran.AlquranScreen
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var route: String
) {
    object AlQuran : BottomNavItem("Al-Qur'an", R.drawable.quran, "al_quran")
    object JadwalSholat : BottomNavItem("Sholat", R.drawable.clock, "sholat")
}

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavigationGraph(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    appBarAlpha: Float
) {
    NavHost(navController, startDestination = BottomNavItem.AlQuran.route) {
        composable(BottomNavItem.AlQuran.route) {
            AlquranScreen(scrollBehavior = scrollBehavior, appBarAlpha = appBarAlpha)
        }
        composable(BottomNavItem.JadwalSholat.route) {
            ContentJadwalSholat()
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
            .height(56.dp)
            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(Gren)
            .padding(6.dp),
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

@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(item: BottomNavItem, isSelected: Boolean, onClick: () -> Unit) {
    val background = if (isSelected) Gold.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (isSelected) Gold else Gold.copy(alpha = 0.5f)

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 18.dp, end = 16.dp),
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
                    textAlign = TextAlign.Center,
                    color = contentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
