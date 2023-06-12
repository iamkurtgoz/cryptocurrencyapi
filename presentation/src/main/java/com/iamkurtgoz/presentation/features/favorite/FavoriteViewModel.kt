package com.iamkurtgoz.presentation.features.favorite

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.domain.usecase.GetUserIsLoginUseCase
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.SideEffect
import com.iamkurtgoz.presentation.core.ViewAction
import com.iamkurtgoz.presentation.core.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val application: Application,
    private val sharedUserState: SharedUserState,
    private val getUserIsLoginUseCase: GetUserIsLoginUseCase
) : CoreViewModel<FavoriteViewModel.State, FavoriteViewModel.Action, FavoriteViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    init {
        viewModelScope.launch {
            val newState = state.value.copy(
                isSignedIn = getUserIsLoginUseCase.invoke()
            )
            updateState(newState)
        }
    }

    override fun dispatch(action: Action) {
    }

    data class State(
        val isSignedIn: Boolean = false
    ) : ViewState

    sealed class Action : ViewAction

    sealed class Effect : SideEffect {
        object RouteToLogin: Effect()
    }
}
