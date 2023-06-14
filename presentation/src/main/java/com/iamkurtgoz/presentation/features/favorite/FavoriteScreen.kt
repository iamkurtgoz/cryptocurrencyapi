package com.iamkurtgoz.presentation.features.favorite

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.core.animation.KLottieView
import com.iamkurtgoz.presentation.core.components.KButtonBackground
import com.iamkurtgoz.presentation.features.coin.CoinViewModel
import com.iamkurtgoz.presentation.features.coin.components.CoinCard
import com.iamkurtgoz.presentation.features.coin.loadStateItem
import com.iamkurtgoz.presentation.features.detail.CoinDetailScreenNavArg
import com.iamkurtgoz.presentation.navigation.Screen
import com.iamkurtgoz.presentation.navigation.ScreenState
import com.iamkurtgoz.presentation.theme.AppShapes
import com.iamkurtgoz.presentation.theme.Blue
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.ColorTextSecondary
import com.iamkurtgoz.presentation.theme.dimens
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel(), navController: NavController) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val viewState = viewModel.state.value
    val dialogState = viewModel.dialogState.value
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(key1 = "") {
        viewModel.sideEffect.collectLatest {
            when(it) {
                is FavoriteViewModel.Effect.RouteToLogin -> {
                    navController.navigate(Screen.Login.route)
                }
                is FavoriteViewModel.Effect.RouteToCoinDetail -> {
                    navController.navigate(CoinDetailScreenNavArg.getRoute(it.id))
                }
            }
        }
    }

    LaunchedEffect(key1 = "") {
        viewModel.dispatch(FavoriteViewModel.Action.SyncLoginStatus)
    }

    LaunchedEffect(key1 = navBackStackEntry?.savedStateHandle) {
        navBackStackEntry?.savedStateHandle?.get<Boolean?>(ScreenState.RouteToBackStackAfterLogin)?.let {
            navBackStackEntry?.savedStateHandle?.remove<Boolean?>(ScreenState.RouteToBackStackAfterLogin)
            viewModel.dispatch(FavoriteViewModel.Action.SyncLoginStatus)
        }
    }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                if (viewState.isContentLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        KLottieView(
                            modifier = Modifier.size(MaterialTheme.dimens.DP_48),
                            res = R.raw.anim_loading
                        )
                    }
                } else if (!viewState.isSignedIn) {
                    LoginFirstView(viewModel = viewModel)
                } else if (viewState.favoritesList.isEmpty()){
                    EmptyFavoritesView()
                } else {
                    val coinList = viewModel.coinPager.collectAsLazyPagingItems()

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        LazyColumn {
                            items(coinList.itemCount) { index ->
                                coinList[index]?.let { item ->
                                    CoinCard(
                                        index,
                                        item,
                                        onClick = {
                                            it.id?.let {
                                                viewModel.updateSideEffect(FavoriteViewModel.Effect.RouteToCoinDetail(it))
                                            }
                                        }
                                    )
                                }
                            }
                            loadStateItem(coinList.loadState.refresh)
                            loadStateItem(coinList.loadState.append)
                        }
                    }
                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun LoginFirstView(viewModel: FavoriteViewModel){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.padding(horizontal = MaterialTheme.dimens.DP_16)) {
            Column(
                modifier = Modifier
                    .padding(all = MaterialTheme.dimens.DP_16),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "ic_lock",
                    colorFilter = ColorFilter.tint(Blue)
                )

                Text(
                    text = stringResource(id = R.string.login_required_favorites_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.DP_8),
                    color = ColorTextPrimary(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = R.string.login_required_favorites_description),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.DP_4),
                    color = ColorTextSecondary(),
                    textAlign = TextAlign.Center
                )

                KButtonBackground(
                    text = stringResource(id = R.string.login),
                    rootModifier = Modifier
                        .padding(top = MaterialTheme.dimens.DP_16),
                ){
                    viewModel.updateSideEffect(FavoriteViewModel.Effect.RouteToLogin)
                }

            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
private fun EmptyFavoritesView(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.padding(horizontal = MaterialTheme.dimens.DP_16)) {
            Column(
                modifier = Modifier
                    .padding(all = MaterialTheme.dimens.DP_16),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "ic_favorite",
                    colorFilter = ColorFilter.tint(Blue)
                )

                Text(
                    text = stringResource(id = R.string.favorite_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.DP_8),
                    color = ColorTextPrimary(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = R.string.your_favorites_empty),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.DP_4),
                    color = ColorTextSecondary(),
                    textAlign = TextAlign.Center
                )

            }
        }

    }
}
