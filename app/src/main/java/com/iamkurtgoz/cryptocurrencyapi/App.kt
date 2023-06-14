package com.iamkurtgoz.cryptocurrencyapi

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.google.firebase.FirebaseApp
import com.iamkurtgoz.data.worker.CheckBitcoinCurrencyWorker
import com.iamkurtgoz.firebase.FirebaseInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var firebase: FirebaseInitializer

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setMinimumLoggingLevel(android.util.Log.INFO)
        .setWorkerFactory(workerFactory)
        .build()

    override fun onCreate() {
        super.onCreate()
        //Firebase Initialize
        firebase.init()

        //Check currency workar initialize
        CheckBitcoinCurrencyWorker.start(workManager)
    }
}
