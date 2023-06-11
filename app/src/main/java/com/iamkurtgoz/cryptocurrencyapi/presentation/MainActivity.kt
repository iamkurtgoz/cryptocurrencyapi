package com.iamkurtgoz.cryptocurrencyapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iamkurtgoz.presentation.PresentationView
import com.iamkurtgoz.presentation.core.SharedUserState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedUserState: SharedUserState

    private var keepSplashScreenOn = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processSplashScreen()
        example()
        setContent {
            if (!keepSplashScreenOn.value) {
                PresentationView(sharedUserState)
            }
        }
    }

    private fun processSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                keepSplashScreenOn.value
            }
        }
    }

    // TODO delete when add splash view model
    private fun example() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(DelayTime)
            keepSplashScreenOn.value = !keepSplashScreenOn.value
        }
    }

    companion object {
        const val DelayTime = 1000L
    }
}
