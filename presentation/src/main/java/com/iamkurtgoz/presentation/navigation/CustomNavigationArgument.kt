package com.iamkurtgoz.presentation.navigation

interface CustomNavigationArgument<T> {
    fun getRoute(param: T): String
}
