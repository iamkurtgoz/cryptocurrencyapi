package com.iamkurtgoz.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun getScreenWidth() = LocalConfiguration.current.screenWidthDp

@Composable
fun getHalfScreenWidth() = LocalConfiguration.current.screenWidthDp / 2

@Composable
fun getScreenWidthDp() = getScreenWidth().dp

@Composable
fun getHalfScreenWidthDp() = getHalfScreenWidth().dp

@Composable
fun getScreenHeight() = LocalConfiguration.current.screenHeightDp

@Composable
fun getHalfScreenHeight() = LocalConfiguration.current.screenHeightDp / 2

@Composable
fun getScreenHeightDp() = getScreenHeight().dp

@Composable
fun getHalfScreenHeightDp() = getHalfScreenHeight().dp
