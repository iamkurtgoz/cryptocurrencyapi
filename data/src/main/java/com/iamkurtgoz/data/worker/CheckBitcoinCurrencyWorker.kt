package com.iamkurtgoz.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.iamkurtgoz.cryptocurrencyapi.data.R
import com.iamkurtgoz.data.local.UserDataStoreManager
import com.iamkurtgoz.data.notification.NotificationCreator
import com.iamkurtgoz.domain.repository.CoinRepository
import com.iamkurtgoz.domain.usecase.GetFavoritesUseCase
import com.iamkurtgoz.domain.usecase.GetUserIsLoginUseCase
import com.iamkurtgoz.local.dao.CoinDao
import com.iamkurtgoz.local.dao.CoinDetailDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapNotNull
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltWorker
class CheckBitcoinCurrencyWorker @Inject constructor(
    private val context: Context,
    private val workerParams: WorkerParameters,
    private val getUserIsLoginUseCase: GetUserIsLoginUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val coinRepository: CoinRepository,
    private val coinDetailDao: CoinDetailDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val isLogin = getUserIsLoginUseCase.invoke()
        if (isLogin) {
            val favorites = getFavoritesUseCase.invoke().firstOrNull()?.mapNotNull { it.id } ?: emptyList()
            //V1: Get only first 250 items
            val coinList = coinRepository.getCoinList(CURRENCY, DEFAULT_PAGE, DEFAULT_PER_PAGE, favorites)
            coinList.forEach { coinUIModel ->
                var coinDetail = coinDetailDao.getCoinDetailById(coinUIModel.id)
                if (coinDetail != null && coinDetail.currentPrice != coinUIModel.currentPrice) {
                    val notificationMessage = context.getString(R.string.your_favorite_coin_price_change, coinDetail.currentPrice, coinUIModel.currentPrice)
                    coinDetail = coinDetail.copy(
                        currentPrice = coinUIModel.currentPrice
                    )
                    coinDetailDao.updateCoin(coinDetail)

                    //Notification
                    val notification = NotificationCreator.createNotification(
                        context,
                        title = "${coinDetail.name} (${coinDetail.symbol?.uppercase()})",
                        content = notificationMessage
                    )
                    NotificationCreator.showNotification(context, 1, notification)
                }
            }
        }

        return Result.success()
    }

    companion object {
        private val TAG = "${CheckBitcoinCurrencyWorker::class.java.simpleName}PeriodicWork"
        private const val REPEAT_INTERVAL = 15L
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PER_PAGE = 250
        private const val CURRENCY = "TRY"

        fun start(workManager: WorkManager){
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

            val notificationWorkRequest = PeriodicWorkRequestBuilder<CheckBitcoinCurrencyWorker>(REPEAT_INTERVAL, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag(TAG)
                .build()

            workManager.apply {
                cancelAllWorkByTag(TAG)
                enqueue(notificationWorkRequest)
            }
        }
    }

}