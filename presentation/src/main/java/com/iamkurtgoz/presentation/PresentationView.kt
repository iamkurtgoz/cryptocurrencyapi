package com.iamkurtgoz.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.navigation.Navigator
import com.iamkurtgoz.presentation.theme.CryptoCurrencyApiTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PresentationView(sharedUserState: SharedUserState) {
    CryptoCurrencyApiTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Navigator(
                sharedUserState = sharedUserState
            )
        }
    }
}
