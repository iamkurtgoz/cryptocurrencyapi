package com.iamkurtgoz.presentation.features.detail

import com.iamkurtgoz.presentation.navigation.CustomNavigationArgument
import com.iamkurtgoz.presentation.navigation.Screen

object CoinDetailScreenNavArg : CustomNavigationArgument<String> {
    const val key: String = "AddressScreenNavArg_key"

    override fun getRoute(param: String): String {
        return "${Screen.CoinDetail.route}?$key=$param"
    }
}
