package com.iamkurtgoz.presentation.features.favorite

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iamkurtgoz.contract.enums.ErrorType
import com.iamkurtgoz.contract.enums.LoadingType
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.domain.usecase.GetFavoritesUseCase
import com.iamkurtgoz.domain.usecase.GetUserIsLoginUseCase
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.SideEffect
import com.iamkurtgoz.presentation.core.ViewAction
import com.iamkurtgoz.presentation.core.ViewState
import com.iamkurtgoz.presentation.features.coin.CoinViewModel
import com.iamkurtgoz.presentation.features.coin.source.CoinPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val application: Application,
    private val sharedUserState: SharedUserState,
    private val getUserIsLoginUseCase: GetUserIsLoginUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val coinPagingSource: CoinPagingSource
) : CoreViewModel<FavoriteViewModel.State, FavoriteViewModel.Action, FavoriteViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    lateinit var coinPager: Flow<PagingData<CoinUIModel>>

    init {
        resetCoinPager()
    }

    private fun resetCoinPager() {
        coinPager = Pager(
            config = PagingConfig(
                pageSize = CoinPagingSource.DEFAULT_PER_PAGE
            ),
            pagingSourceFactory = {
                coinPagingSource
            },
            initialKey = CoinPagingSource.Params(
                vsCurrency = CoinPagingSource.CURRENCY,
                page = CoinPagingSource.DEFAULT_PAGE,
                perPage = CoinPagingSource.DEFAULT_PER_PAGE,
                isFavorite = true
            )
        ).flow.cachedIn(viewModelScope)
    }


    override fun dispatch(action: Action) {
        when(action) {
            is Action.SyncLoginStatus -> viewModelScope.launch {
                val newState = state.value.copy(
                    isSignedIn = getUserIsLoginUseCase.invoke()
                )
                updateState(newState)
                if (state.value.isSignedIn) {
                    resetCoinPager()
                    dispatch(Action.GetFavorites)
                } else {
                    updateState(state.value.copy(
                        isContentLoading = false,
                    ))
                }
            }
            is Action.GetFavorites -> {
                sendRequestWithoutRest(
                    loadingType = LoadingType.Custom,
                    errorType = ErrorType.Popup,
                    call = { getFavoritesUseCase.invoke().map { it.mapNotNull { it.id } } },
                    onLoadingCallback = {
                        updateState(state.value.copy(
                            isContentLoading = true
                        ))
                    },
                    onSuccess = {
                        updateState(state.value.copy(
                            isContentLoading = false,
                            favoritesList = it
                        ))
                    }
                )
            }
        }
    }

    data class State(
        val isContentLoading: Boolean = true,
        val isSignedIn: Boolean = false,
        val favoritesList: List<String> = emptyList()
    ) : ViewState

    sealed class Action : ViewAction {
        object SyncLoginStatus: Action()
        object GetFavorites: Action()
    }

    sealed class Effect : SideEffect {
        object RouteToLogin: Effect()
        data class RouteToCoinDetail(val id: String): Effect()
    }
}
