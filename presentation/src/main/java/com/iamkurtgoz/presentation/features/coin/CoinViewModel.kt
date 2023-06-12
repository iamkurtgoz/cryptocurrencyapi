package com.iamkurtgoz.presentation.features.coin

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.presentation.core.CoreViewModel
import com.iamkurtgoz.presentation.core.SharedUserState
import com.iamkurtgoz.presentation.core.SideEffect
import com.iamkurtgoz.presentation.core.ViewAction
import com.iamkurtgoz.presentation.core.ViewState
import com.iamkurtgoz.presentation.features.coin.source.CoinPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class CoinViewModel @Inject constructor(
    private val application: Application,
    private val sharedUserState: SharedUserState,
    private val coinPagingSource: CoinPagingSource
) : CoreViewModel<CoinViewModel.State, CoinViewModel.Action, CoinViewModel.Effect>(
    initialState = State(),
    sharedUserState = sharedUserState
) {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchList = MutableStateFlow(emptyList<CoinUIModel>())
    val searchList = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_searchList) { text, list ->
            if (text.isBlank()) {
                return@combine list
            } else {
                delay(2000L)
                list.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _searchList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    val coinPager: Flow<PagingData<CoinUIModel>> = Pager(
        config = PagingConfig(
            pageSize = CoinPagingSource.DEFAULT_PER_PAGE
        ),
        pagingSourceFactory = {
            coinPagingSource
        },
        initialKey = CoinPagingSource.Params(
            vsCurrency = CoinPagingSource.CURRENCY,
            page = CoinPagingSource.DEFAULT_PAGE,
            perPage = CoinPagingSource.DEFAULT_PER_PAGE
        )
    ).flow.cachedIn(viewModelScope)

    override fun dispatch(action: Action) {
        when (action) {
            is Action.UpdateList -> {
                _searchList.value = action.list
            }
        }
    }

    data class State(
        val any: Any = Any()
    ) : ViewState

    sealed class Action : ViewAction {
        data class UpdateList(val list: List<CoinUIModel>) : Action()
    }

    sealed class Effect : SideEffect
}
