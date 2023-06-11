package com.iamkurtgoz.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.iamkurtgoz.cryptocurrencyapi.presentation.R

val AppFontFamily = FontFamily(
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold)
)

val AppTypography: Typography
    @Composable
    @ReadOnlyComposable
    get() = Typography(
        displayLarge = MaterialTheme.typography.displayLarge.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        displayMedium = MaterialTheme.typography.displayMedium.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        displaySmall = MaterialTheme.typography.displaySmall.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        titleLarge = MaterialTheme.typography.titleLarge.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        titleMedium = MaterialTheme.typography.titleMedium.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        titleSmall = MaterialTheme.typography.titleSmall.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        bodySmall = MaterialTheme.typography.bodySmall.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        labelLarge = MaterialTheme.typography.labelLarge.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        labelMedium = MaterialTheme.typography.labelMedium.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        labelSmall = MaterialTheme.typography.labelSmall.copy(
            fontFamily = AppFontFamily,
            color = MaterialTheme.colorScheme.surface,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
