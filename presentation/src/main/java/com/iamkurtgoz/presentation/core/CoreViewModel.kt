package com.iamkurtgoz.presentation.core

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.contract.enums.ErrorType
import com.iamkurtgoz.contract.enums.LoadingType
import com.iamkurtgoz.contract.model.RestResult
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.core.extensions.onError
import com.iamkurtgoz.presentation.core.extensions.onLoading
import com.iamkurtgoz.presentation.core.extensions.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

abstract class CoreViewModel<S : ViewState, A : ViewAction, E : SideEffect>(
    initialState: S,
    private val sharedUserState: SharedUserState
) : ViewModel() {

    private var _viewModelState: MutableState<ViewModelState> = mutableStateOf(ViewModelState.ShowContent)
    val viewModelState: State<ViewModelState> = _viewModelState

    private val _state = mutableStateOf(initialState)
    val state: State<S> = _state

    private val _dialogState: MutableState<DialogState> = mutableStateOf(DialogState.ShowContent)
    val dialogState: State<DialogState> = _dialogState

    private val _sideEffect = MutableSharedFlow<E>()
    val sideEffect = _sideEffect.asSharedFlow()

    abstract fun dispatch(action: A)

    fun updateState(newState: S) {
        if (newState != _state.value) {
            _state.value = newState
        }
    }

    fun updateDialogState(newState: DialogState) {
        if (newState != _dialogState.value) {
            _dialogState.value = newState
        }
    }

    fun updateSideEffect(effect: E) {
        viewModelScope.launch { _sideEffect.emit(effect) }
    }

    fun setViewModelState(viewModelState: ViewModelState) {
        _viewModelState.value = viewModelState
    }

    sealed class ViewModelState {
        object ShowLoadingDialog : ViewModelState()
        object ShowContent : ViewModelState()
    }

    fun showBaseViewModelStateLoading() = viewModelScope.launch {
        sharedUserState.setUserEffect(SharedUserState.Effect.HideAlert)
        setViewModelState(ViewModelState.ShowLoadingDialog)
    }

    fun showErrorMessage(exception: Throwable) = viewModelScope.launch {
        sharedUserState.setUserEffect(SharedUserState.Effect.HideAlert)
        setViewModelState(ViewModelState.ShowContent)
        sharedUserState.setUserEffect(SharedUserState.Effect.ShowAlertInfo(null, exception.message))
    }

    fun showContent() = viewModelScope.launch {
        sharedUserState.setUserEffect(SharedUserState.Effect.HideAlert)
        setViewModelState(ViewModelState.ShowContent)
    }

    fun showAlertInfo(
        title: String? = null,
        message: String? = null,
        @StringRes buttonTitleResource: Int = R.string.ok,
        cancelable: Boolean = true
    ) = viewModelScope.launch {
        sharedUserState.setUserEffect(
            SharedUserState.Effect.ShowAlertInfo(title, message, buttonTitleResource, cancelable)
        )
    }

    protected fun <T : Any> sendRequest(
        loadingType: LoadingType,
        errorType: ErrorType,
        call: () -> Flow<RestResult<T>>,
        onLoadingCallback: (LoadingType) -> Unit = {},
        onErrorCallback: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ) {
        call.invoke()
            .onLoading {
                showContent()
                if (loadingType == LoadingType.Popup) {
                    showBaseViewModelStateLoading()
                } else {
                    onLoadingCallback(loadingType)
                }
            }
            .onError {
                showContent()
                if (errorType == ErrorType.Popup) {
                    showAlertInfo(title = null, message = it.message, buttonTitleResource = R.string.ok)
                } else {
                    onErrorCallback(it)
                }
            }
            .onSuccess {
                onSuccess.invoke(it)
            }
            .launchIn(viewModelScope)
    }
}

interface ViewState
interface ViewAction
interface SideEffect
interface DialogState {
    object ShowContent : DialogState
}
