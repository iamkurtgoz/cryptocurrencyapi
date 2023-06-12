package com.iamkurtgoz.presentation.features.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.contract.enums.ErrorType
import com.iamkurtgoz.contract.enums.LoadingType
import com.iamkurtgoz.domain.usecase.GetUserIsLoginUseCase
import com.iamkurtgoz.domain.usecase.LoginOrRegisterUseCase
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.SideEffect
import com.iamkurtgoz.presentation.core.ViewAction
import com.iamkurtgoz.presentation.core.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    sharedUserState: SharedUserState,
    private val getUserIsLoginUseCase: GetUserIsLoginUseCase,
    private val loginOrRegisterUseCase: LoginOrRegisterUseCase
) : CoreViewModel<LoginViewModel.State, LoginViewModel.Action, LoginViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    val emailAddress: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(""))
    val passwordAddress: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(""))

    init {
        viewModelScope.launch {
            val newState = state.value.copy(
                isSignedIn = getUserIsLoginUseCase.invoke()
            )
            updateState(newState)
        }
    }

    override fun dispatch(action: Action) {
        when(action) {
            is Action.Login -> {
                /*
                sendRequest(
                    loadingType = LoadingType.Popup,
                    errorType = ErrorType.Popup,
                    call = {  }
                )
                 */
            }
        }
    }

    private fun isValid(): Boolean {

        return true
    }

    data class State(
        val isSignedIn: Boolean = false
    ) : ViewState

    sealed class Action : ViewAction {
        object Login: Action()
    }

    sealed class Effect : SideEffect
}
