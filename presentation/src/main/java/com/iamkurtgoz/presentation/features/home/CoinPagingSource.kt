package com.iamkurtgoz.presentation.features.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.domain.repository.CoinRepository
import javax.inject.Inject

class CoinPagingSource @Inject constructor(
    private val repository: CoinRepository
): PagingSource<CoinPagingSource.Params, CoinUIModel>() {

    override fun getRefreshKey(state: PagingState<Params, CoinUIModel>): Params? {
        return state.anchorPosition?.let { anchorPosition ->
            val prevKey = state.closestPageToPosition(anchorPosition)?.prevKey
            prevKey?.copy(
                page = prevKey.page?.plus(DEFAULT_PAGE_INCREMENT) ?: prevKey.page?.minus(DEFAULT_PAGE_INCREMENT)
            )
        }
    }

    override suspend fun load(params: LoadParams<Params>): LoadResult<Params, CoinUIModel> {
        return try {
            val page = params.key?.page ?: DEFAULT_PAGE_INCREMENT
            val list = repository.getCoinList(
                vsCurrency = params.key?.vsCurrency,
                page = params.key?.page,
                perPage = params.key?.perPage,
            )

            val endOfPaginationReached = list.isEmpty() || list.size < (params.key?.perPage ?: DEFAULT_PER_PAGE)
            LoadResult.Page(
                data = list,
                prevKey = if (page == 1) null else params.key?.copy(page = page.minus(DEFAULT_PAGE_INCREMENT)),
                nextKey = if (endOfPaginationReached) null else params.key?.copy(page = page.plus(DEFAULT_PAGE_DECREMENT)),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    data class Params(
        val vsCurrency: String?,
        val page: Int?,
        val perPage: Int?
    )

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_INCREMENT = 1
        const val DEFAULT_PAGE_DECREMENT = 1
        const val DEFAULT_PER_PAGE = 250
        const val CURRENCY = "TRY"
    }
}