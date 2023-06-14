package com.iamkurtgoz.presentation.features.login

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.contract.enums.ErrorType
import com.iamkurtgoz.contract.enums.LoadingType
import com.iamkurtgoz.core.isEmailVerify
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
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
    private val application: Application,
    sharedUserState: SharedUserState,
    private val getUserIsLoginUseCase: GetUserIsLoginUseCase,
    private val loginOrRegisterUseCase: LoginOrRegisterUseCase
) : CoreViewModel<LoginViewModel.State, LoginViewModel.Action, LoginViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    val emailAddress: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("kurtgozmehmet159@gmail.com"))
    val password: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("123456"))

    init {
        viewModelScope.launch {
            val newState = state.value.copy(
                isSignedIn = getUserIsLoginUseCase.invoke()
            )
            updateState(newState)
        }
    }

    override fun dispatch(action: Action) {
        when (action) {
            is Action.Login -> viewModelScope.launch {
                if (isValid()) {
                    sendRequestWithoutRest(
                        loadingType = LoadingType.Popup,
                        errorType = ErrorType.Popup,
                        call = { loginOrRegisterUseCase.invoke(emailAddress.value.text, password.value.text) },
                        onSuccess = {
                            showContent()
                            updateSideEffect(Effect.RouteToBack)
                        }
                    )
                }
            }
        }
    }

    private fun isValid(): Boolean {
        if (!emailAddress.value.text.isEmailVerify()) {
            showAlertInfo(title = null, message = application.getString(R.string.email_address_not_verify))
            return false
        }
        if (password.value.text.isEmpty() || password.value.text.length <= 4) {
            showAlertInfo(title = null, message = application.getString(R.string.password_more_than_4_characters))
            return false
        }
        return true
    }

    data class State(
        val isSignedIn: Boolean = false
    ) : ViewState

    sealed class Action : ViewAction {
        object Login : Action()
    }

    sealed class Effect : SideEffect {
        object RouteToBack : Effect()
    }
}
