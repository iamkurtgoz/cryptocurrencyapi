package com.iamkurtgoz.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF1E88E5)
val DarkGray = Color(0xFF121212)
val LightGray = Color(0xFFEEEEEE)
val SurfaceDark = Color(0xFF121212)
val Red20 = Color(0xFFB00020)
val SurfaceLight = Color(0xFFF5F5F5)
val ColorTextPrimaryLight = Color(0xFF000000)
val ColorTextPrimaryNight = Color(0xFFFFFFFF)
val ColorTextSecondaryLight = Color(0xFF8D8E8E)
val ColorTextSecondaryNight = Color(0xFF8D8E8E)

@Composable
fun ColorBackground(): Color = if (!isSystemInDarkTheme()) LightGray else DarkGray

@Composable
fun ColorTextPrimary(): Color =
    if (!isSystemInDarkTheme()) ColorTextPrimaryLight else ColorTextPrimaryNight

@Composable
fun ColorTextSecondary(): Color =
    if (!isSystemInDarkTheme()) ColorTextSecondaryLight else ColorTextSecondaryNight
