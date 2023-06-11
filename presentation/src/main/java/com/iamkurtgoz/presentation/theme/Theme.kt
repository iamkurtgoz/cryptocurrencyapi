package com.iamkurtgoz.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = ColorTextPrimaryNight,
    secondary = ColorTextSecondaryNight,
    onSecondary = Color.Black,
    background = DarkGray,
    onBackground = Color.White,
    surface = SurfaceDark,
    onSurface = Color.White,
    error = Red20,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = ColorTextPrimaryLight,
    secondary = ColorTextSecondaryLight,
    onSecondary = Color.Black,
    background = LightGray,
    onBackground = Color.Black,
    surface = SurfaceLight,
    onSurface = Color.Black,
    error = Red20,
    onError = Color.White
)

@Composable
fun CryptoCurrencyApiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            //Status Bar Color
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

            //Navigation Bar Color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.navigationBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
            } else {
                window.navigationBarColor = colorScheme.primary.toArgb()
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
