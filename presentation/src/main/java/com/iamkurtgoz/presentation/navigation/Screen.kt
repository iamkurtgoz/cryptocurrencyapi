package com.iamkurtgoz.presentation.navigation

sealed class Screen(val route: String) {

    // HOME
    object Coin : Screen("coin")
    object Favorite : Screen("favorite")
    object CoinDetail : Screen("coinDetail")
    object Login : Screen("login")
}
