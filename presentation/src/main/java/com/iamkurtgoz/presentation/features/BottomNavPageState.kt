package com.iamkurtgoz.presentation.features

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavBackStackEntry
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.navigation.Screen

sealed class BottomNavPageState(
    @StringRes var titleResource: Int,
    @DrawableRes var icon: Int,
    var route: String
) {
    object Coin : BottomNavPageState(
        R.string.coin_title,
        R.drawable.ic_bitcoin,
        Screen.Coin.route
    )

    object Favorite : BottomNavPageState(
        R.string.favorite_title,
        R.drawable.ic_favorite,
        Screen.Favorite.route
    )
}

fun List<BottomNavPageState>.bottomNavIsActive(navBackStackEntry: NavBackStackEntry?): Boolean {
    val current = navBackStackEntry?.destination
    if (this.find { it.route == current?.route } != null) return true
    return false
}

fun BottomNavPageState.isSelected(navBackStackEntry: NavBackStackEntry?): Boolean {
    val current = navBackStackEntry?.destination
    if (this.route == current?.route) return true
    return false
}
