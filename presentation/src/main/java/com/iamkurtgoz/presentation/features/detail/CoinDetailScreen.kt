package com.iamkurtgoz.presentation.features.detail

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.domain.extensions.formatDecimal
import com.iamkurtgoz.presentation.core.animation.KLottieView
import com.iamkurtgoz.presentation.core.components.KButtonBackground
import com.iamkurtgoz.presentation.core.getScreenWidth
import com.iamkurtgoz.presentation.navigation.Screen
import com.iamkurtgoz.presentation.navigation.ScreenState
import com.iamkurtgoz.presentation.theme.Blue
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.dimens
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val viewState = viewModel.state.value
    val dialogState = viewModel.dialogState.value
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = "") {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is CoinDetailViewModel.Effect.RouteToLogin -> {
                    navController.navigate(Screen.Login.route)
                }
            }
        }
    }

    LaunchedEffect(key1 = navBackStackEntry?.savedStateHandle) {
        navBackStackEntry?.savedStateHandle?.get<Boolean?>(ScreenState.RouteToBackStackAfterLogin)?.let {
            navBackStackEntry?.savedStateHandle?.remove<Boolean?>(ScreenState.RouteToBackStackAfterLogin)
            viewModel.dispatch(CoinDetailViewModel.Action.SyncLoginStatus)
        }
    }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (viewState.isContentLoading || viewState.coinData == null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        KLottieView(
                            modifier = Modifier.size(MaterialTheme.dimens.DP_48),
                            res = R.raw.anim_loading
                        )
                    }
                } else {
                    GlideImage(
                        imageModel = { viewState.coinData.image },
                        imageOptions = ImageOptions(
                            requestSize = IntSize(getScreenWidth() / 2, getScreenWidth() / 2)
                        ),
                        modifier = Modifier
                            .padding(top = MaterialTheme.dimens.DP_48)
                    )

                    Column(
                        modifier = Modifier
                            .padding(all = MaterialTheme.dimens.DP_16),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.hash).uppercase(),
                            color = ColorTextPrimary(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )

                        Text(
                            text = viewState.coinData.hashingAlgorithm?.uppercase() ?: "-",
                            color = ColorTextPrimary(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = MaterialTheme.dimens.DP_32),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = MaterialTheme.dimens.DP_16),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "${viewState.coinData.name} (${viewState.coinData.symbol?.uppercase()})",
                                color = ColorTextPrimary(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Normal
                                )
                            )

                            Text(
                                text = viewState.coinData.currentPrice?.formatDecimal ?: "-",
                                color = ColorTextPrimary(),
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = MaterialTheme.dimens.DP_16),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = stringResource(id = R.string.last_24h_change),
                                color = ColorTextPrimary(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Normal
                                )
                            )

                            Text(
                                text = viewState.coinData.priceChangePercentage24h?.let { "$it%" } ?: kotlin.run { "-" },
                                color = if (viewState.coinData.isPricePercentage24hUpper) Color.Green else Color.Red,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = MaterialTheme.dimens.DP_32)
                            .padding(horizontal = MaterialTheme.dimens.DP_16),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        KButtonBackground(
                            text = stringResource(id = R.string.refresh),
                            isFullWidth = false,
                            rootModifier = Modifier.weight(1f)
                                .padding(end = MaterialTheme.dimens.DP_8)
                        ) {
                            if (viewState.isSignedIn) {
                                viewModel.dispatch(CoinDetailViewModel.Action.GetCoinDetail(withCache = false))
                            } else {
                                viewModel.updateSideEffect(CoinDetailViewModel.Effect.RouteToLogin)
                            }
                        }

                        KButtonBackground(
                            text = stringResource(
                                id = if (viewState.isFavorite) R.string.remove_from_favorites else R.string.add_to_favorites
                            ),
                            isFullWidth = false,
                            rootModifier = Modifier.weight(1f)
                                .padding(start = MaterialTheme.dimens.DP_8),
                            containerColor = if (viewState.isFavorite) Color.Red else Blue
                        ) {
                            if (viewState.isSignedIn) {
                                if (viewState.isFavorite) {
                                    viewModel.dispatch(CoinDetailViewModel.Action.RemoveFavorites)
                                } else {
                                    viewModel.dispatch(CoinDetailViewModel.Action.AddFavorites)
                                }
                            } else {
                                viewModel.updateSideEffect(CoinDetailViewModel.Effect.RouteToLogin)
                            }
                        }
                    }

                    Text(
                        text = viewState.coinData.description ?: "",
                        color = ColorTextPrimary(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(all = MaterialTheme.dimens.DP_16)
                    )
                }
            }
        }
    )
}
