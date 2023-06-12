package com.iamkurtgoz.cryptocurrencyapi

import android.app.Application
import com.google.firebase.FirebaseApp
import com.iamkurtgoz.firebase.FirebaseInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var firebase: FirebaseInitializer
    override fun onCreate() {
        super.onCreate()
        firebase.init()
    }
}
