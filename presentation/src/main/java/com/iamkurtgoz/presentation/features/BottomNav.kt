package com.iamkurtgoz.presentation.features

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamkurtgoz.presentation.theme.Blue
import com.iamkurtgoz.presentation.theme.ColorBackground
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.ColorTextSecondary
import com.iamkurtgoz.presentation.theme.dimens

@Composable
@ExperimentalFoundationApi
fun BottomNav(navController: NavController) {
    // Variable
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val items = listOf(
        BottomNavPageState.Coin,
        BottomNavPageState.Favorite
    )

    if (items.bottomNavIsActive(navBackStackEntry)) {
        NavigationBar(
            modifier = Modifier
                .shadow(
                    elevation = MaterialTheme.dimens.DP_8,
                    ambientColor = ColorTextPrimary()
                ),
            containerColor = ColorBackground()
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = item.isSelected(navBackStackEntry),
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = ""
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.titleResource),
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis,
                            color = if (item.isSelected(navBackStackEntry)) ColorTextPrimary() else ColorTextSecondary()
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ColorTextPrimary(),
                        unselectedIconColor = ColorTextSecondary(),
                        unselectedTextColor = ColorTextSecondary(),
                        indicatorColor = Blue
                    ),
                    alwaysShowLabel = true
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    BottomNav(navController = rememberNavController())
}
