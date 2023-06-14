package com.iamkurtgoz.presentation.core.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

// Fill max size
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.fillMaxSizeStatus(active: Boolean, fraction: Float = 1f): Modifier {
    return if (active) {
        this.fillMaxSize(fraction)
    } else {
        this
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.fillMaxWidthStatus(active: Boolean, fraction: Float = 1f): Modifier {
    return if (active) {
        this.fillMaxWidth(fraction)
    } else {
        this
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.fillMaxHeightStatus(active: Boolean, fraction: Float = 1f): Modifier {
    return if (active) {
        this.fillMaxHeight(fraction)
    } else {
        this
    }
}
