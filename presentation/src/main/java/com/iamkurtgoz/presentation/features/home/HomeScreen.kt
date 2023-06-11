package com.iamkurtgoz.presentation.features.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.iamkurtgoz.presentation.core.animation.KLottieView
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.dimens
import com.iamkurtgoz.cryptocurrencyapi.presentation.R

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val viewState = viewModel.state.value
    val dialogState = viewModel.dialogState.value
    val coinList = viewModel.coinPager.collectAsLazyPagingItems()

    LaunchedEffect(key1 = coinList.itemSnapshotList.items) {
        viewModel.dispatch(HomeViewModel.Action.UpdateList(coinList.itemSnapshotList.items))
    }

    val searchText by viewModel.searchText.collectAsState()
    val searchList by viewModel.searchList.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Scaffold(
        topBar = {
            SearchViewTextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                if (searchText.isEmpty()) {
                    LazyColumn {
                        items(coinList.itemCount) { index ->
                            coinList[index]?.let { item ->
                                CoinCard(
                                    index, item
                                )
                            }
                        }
                        loadStateItem(coinList.loadState.refresh)
                        loadStateItem(coinList.loadState.append)
                    }
                } else {
                    if(isSearching) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        LazyColumn {
                            items(searchList.size) { index ->
                                searchList[index].let { item ->
                                    CoinCard(
                                        index, item
                                    )
                                }
                            }
                        }
                    }
                }

            }

        }
    )
}

private fun LazyListScope.loadStateItem(loadState: LoadState) {
    when (loadState) {
        is LoadState.Error -> {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = stringResource(id = R.string.error_unknown),
                        color = Color.Red
                    )
                }
            }
        }
        is LoadState.Loading -> {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Loading"
                    )

                    CircularProgressIndicator(color = Color.Black)
                }
            }
        }
        else -> {}
    }
}