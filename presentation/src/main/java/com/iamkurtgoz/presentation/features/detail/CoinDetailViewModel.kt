package com.iamkurtgoz.presentation.features.detail

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.contract.enums.ErrorType
import com.iamkurtgoz.contract.enums.LoadingType
import com.iamkurtgoz.domain.combineResults
import com.iamkurtgoz.domain.model.CoinDetailUIModel
import com.iamkurtgoz.domain.model.FavoriteUIModel
import com.iamkurtgoz.domain.usecase.AddFavoritesUseCase
import com.iamkurtgoz.domain.usecase.GetCoinDetailUseCase
import com.iamkurtgoz.domain.usecase.GetFavoritesUseCase
import com.iamkurtgoz.domain.usecase.GetUserIsLoginUseCase
import com.iamkurtgoz.domain.usecase.RemoteFavoritesUseCase
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.SideEffect
import com.iamkurtgoz.presentation.core.ViewAction
import com.iamkurtgoz.presentation.core.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val application: Application,
    private val sharedUserState: SharedUserState,
    private val savedStateHandle: SavedStateHandle,
    private val getUserIsLoginUseCase: GetUserIsLoginUseCase,
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoritesUseCase: AddFavoritesUseCase,
    private val remoteFavoritesUseCase: RemoteFavoritesUseCase
) : CoreViewModel<CoinDetailViewModel.State, CoinDetailViewModel.Action, CoinDetailViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    init {
        savedStateHandle.get<String?>(CoinDetailScreenNavArg.key)?.let {
            updateState(state.value.copy(coinId = it))
        }
        dispatch(Action.SyncLoginStatus)
        dispatch(Action.GetCoinDetail(withCache = true))
        dispatch(Action.RefreshWithDelay)
    }

    override fun dispatch(action: Action) {
        when(action) {
            is Action.SyncLoginStatus -> viewModelScope.launch {
                val newState = state.value.copy(
                    isSignedIn = getUserIsLoginUseCase.invoke()
                )
                updateState(newState)
                dispatch(Action.GetCoinDetail(withCache = true))
            }
            is Action.GetCoinDetail -> {
                updateState(state.value.copy(
                    isContentLoading = true
                ))
                combine(
                    getCoinDetailUseCase.invoke(state.value.coinId, action.withCache),
                    getFavoritesUseCase.invoke()
                ) { results ->
                    val coinData = results[0] as? CoinDetailUIModel?
                    val favorites = results[1] as? List<FavoriteUIModel>?
                    val favoriteModel = favorites?.firstOrNull { it.id == state.value.coinId }
                    updateState(state.value.copy(
                        isContentLoading = false,
                        coinData = coinData,
                        isFavorite = favoriteModel != null,
                        favoriteModel = favoriteModel
                    ))
                }.catch {
                    showErrorMessage(it)
                }.launchIn(viewModelScope)
            }
            is Action.AddFavorites -> {
                sendRequestWithoutRest(
                    loadingType = LoadingType.Popup,
                    errorType = ErrorType.Popup,
                    call = { addFavoritesUseCase.invoke(state.value.coinId) },
                    onSuccess = {
                        val favoriteModel = it.firstOrNull { it.id == state.value.coinId }
                        updateState(state.value.copy(
                            isFavorite = favoriteModel != null,
                            favoriteModel = favoriteModel
                        ))
                        showContent()
                    }
                )
            }
            is Action.RemoveFavorites -> {
                sendRequestWithoutRest(
                    loadingType = LoadingType.Popup,
                    errorType = ErrorType.Popup,
                    call = { remoteFavoritesUseCase.invoke(state.value.coinId, state.value.favoriteModel?.documentId) },
                    onSuccess = {
                        val favoriteModel = it.firstOrNull { it.id == state.value.coinId }
                        updateState(state.value.copy(
                            isFavorite = favoriteModel != null,
                            favoriteModel = favoriteModel
                        ))
                        showContent()
                    }
                )
            }
            is Action.RefreshWithDelay -> viewModelScope.launch(Dispatchers.IO) {
                delay(1000 * 60)
                dispatch(Action.GetCoinDetail(withCache = true))
                dispatch(Action.RefreshWithDelay)
            }
        }
    }

    data class State(
        val isContentLoading: Boolean = false,
        val isSignedIn: Boolean = false,
        val coinId: String? = null,
        val coinData: CoinDetailUIModel? = null,
        val isFavorite: Boolean = false,
        val favoriteModel: FavoriteUIModel? = null
    ) : ViewState

    sealed class Action : ViewAction {
        object SyncLoginStatus: Action()
        data class GetCoinDetail(val withCache: Boolean = true): Action()
        object AddFavorites: Action()
        object RemoveFavorites: Action()
        object RefreshWithDelay: Action()
    }

    sealed class Effect : SideEffect {
        object RouteToLogin: Effect()
    }
}
