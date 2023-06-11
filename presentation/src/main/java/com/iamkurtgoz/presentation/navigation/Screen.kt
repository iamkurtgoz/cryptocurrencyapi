package com.iamkurtgoz.presentation.navigation

sealed class Screen(val route: String) {

    // HOME
    object Home : Screen("home")
}
