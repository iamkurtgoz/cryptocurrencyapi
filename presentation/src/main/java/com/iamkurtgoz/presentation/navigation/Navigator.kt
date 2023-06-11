package com.iamkurtgoz.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.animation.KLottieProgressHUD
import com.iamkurtgoz.presentation.features.home.HomeScreen
import com.iamkurtgoz.presentation.theme.AppShapes
import com.iamkurtgoz.presentation.theme.ColorBackground
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.ColorTextSecondary
import com.iamkurtgoz.presentation.theme.dimens
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigator(sharedUserState: SharedUserState) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    var alertState by remember { mutableStateOf<SharedUserState.Effect.ShowAlertInfo?>(null) }

    LaunchedEffect(key1 = "") {
        sharedUserState.userEffect.collectLatest {
            when (it) {
                is SharedUserState.Effect.HideAlert -> {
                    alertState = null
                }
                is SharedUserState.Effect.ShowAlertInfo -> {
                    alertState = it
                }
                else -> {
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = customHiltViewModel(),
                    navController = navController
                )
            }
        }
    }

    alertState?.let { state ->
        AlertDialog(
            shape = AppShapes.extraSmall,
            containerColor = ColorBackground(),
            onDismissRequest = { alertState = null },
            title = {
                Text(
                    text = state.title ?: "",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    color = ColorTextPrimary()
                )
            },
            text = {
                Text(
                    text = state.message ?: "",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    color = ColorTextSecondary()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { alertState = null }
                ) {
                    Text(
                        text = stringResource(id = alertState?.buttonTitleResources ?: R.string.ok),
                        color = ColorTextPrimary()
                    )
                }
            }
        )
    }
}

@Composable
inline fun <S, A, E, reified VM : CoreViewModel<S, A, E>> customHiltViewModel(): VM {
    val hiltViewModel = hiltViewModel<VM>()

    when(hiltViewModel.viewModelState.value) {
        is CoreViewModel.ViewModelState.ShowLoadingDialog -> {
            KLottieProgressHUD(
                size = MaterialTheme.dimens.DP_56
            )
        }
        else -> {}
    }
    return hiltViewModel
}

fun NavController.navigatePopUpToInclusive(route: String) {
    val option = NavOptions.Builder()
    option.setPopUpTo(currentDestination?.route, true)
    navigate(route, option.build())
}

fun NavController.navigateAndClearBackStack(route: String) {
    navigate(route) {
        popUpTo(0)
    }
}
