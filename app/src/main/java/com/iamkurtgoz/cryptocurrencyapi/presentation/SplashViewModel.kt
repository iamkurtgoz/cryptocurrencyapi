package com.iamkurtgoz.cryptocurrencyapi.presentation

import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.SideEffect
import com.iamkurtgoz.presentation.core.ViewAction
import com.iamkurtgoz.presentation.core.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedUserState: SharedUserState
) : CoreViewModel<SplashViewModel.State, SplashViewModel.Action, SplashViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    init {
        dispatch(Action.RouteToHome)
    }

    override fun dispatch(action: Action) {
        when (action) {
            is Action.RouteToHome -> viewModelScope.launch {
                delay(DelayTime)
                val newState = state.value.copy(
                    keepSplashScreenOn = false
                )
                updateState(newState)
            }
        }
    }

    data class State(
        val keepSplashScreenOn: Boolean = true
    ) : ViewState

    sealed class Action : ViewAction {
        object RouteToHome : Action()
    }

    sealed class Effect : SideEffect

    companion object {
        const val DelayTime = 1000L
    }
}
