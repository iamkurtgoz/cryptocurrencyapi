package com.iamkurtgoz.presentation.core

import androidx.annotation.StringRes
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Singleton

@Singleton
class SharedUserState {
    private val _userEffect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val userEffect: SharedFlow<Effect> = _userEffect.asSharedFlow()

    suspend fun setUserEffect(effect: Effect) {
        _userEffect.emit(effect)
    }

    suspend fun clearUserEffect() {
        setUserEffect(Effect.None)
    }

    sealed class Effect {
        object None : Effect()
        data class ShowAlertInfo(
            val title: String? = null,
            val message: String? = null,
            @StringRes val buttonTitleResources: Int = R.string.ok,
            val cancelable: Boolean = true
        ) : Effect()
        object HideAlert : Effect()
    }
}
