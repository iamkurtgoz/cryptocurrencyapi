package com.iamkurtgoz.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val DP_0: Dp = 0.dp,
    val DP_1: Dp = 1.dp,
    val DP_2: Dp = 2.dp,
    val DP_3: Dp = 3.dp,
    val DP_4: Dp = 4.dp,
    val DP_5: Dp = 5.dp,
    val DP_6: Dp = 6.dp,
    val DP_7: Dp = 7.dp,
    val DP_8: Dp = 8.dp,
    val DP_9: Dp = 9.dp,
    val DP_10: Dp = 10.dp,
    val DP_11: Dp = 11.dp,
    val DP_12: Dp = 12.dp,
    val DP_13: Dp = 13.dp,
    val DP_14: Dp = 14.dp,
    val DP_15: Dp = 15.dp,
    val DP_16: Dp = 16.dp,
    val DP_17: Dp = 17.dp,
    val DP_18: Dp = 18.dp,
    val DP_19: Dp = 19.dp,
    val DP_20: Dp = 20.dp,
    val DP_21: Dp = 21.dp,
    val DP_22: Dp = 22.dp,
    val DP_23: Dp = 23.dp,
    val DP_24: Dp = 24.dp,
    val DP_25: Dp = 25.dp,
    val DP_26: Dp = 26.dp,
    val DP_27: Dp = 27.dp,
    val DP_28: Dp = 28.dp,
    val DP_29: Dp = 29.dp,
    val DP_30: Dp = 30.dp,
    val DP_31: Dp = 31.dp,
    val DP_32: Dp = 32.dp,
    val DP_33: Dp = 33.dp,
    val DP_34: Dp = 34.dp,
    val DP_35: Dp = 35.dp,
    val DP_36: Dp = 36.dp,
    val DP_37: Dp = 37.dp,
    val DP_38: Dp = 38.dp,
    val DP_39: Dp = 39.dp,
    val DP_40: Dp = 40.dp,
    val DP_41: Dp = 41.dp,
    val DP_42: Dp = 42.dp,
    val DP_43: Dp = 43.dp,
    val DP_44: Dp = 44.dp,
    val DP_45: Dp = 45.dp,
    val DP_46: Dp = 46.dp,
    val DP_47: Dp = 47.dp,
    val DP_48: Dp = 48.dp,
    val DP_49: Dp = 49.dp,
    val DP_50: Dp = 50.dp,
    val DP_52: Dp = 52.dp,
    val DP_56: Dp = 56.dp,
    val DP_60: Dp = 60.dp,
    val DP_64: Dp = 64.dp,
    val DP_68: Dp = 68.dp,
    val DP_72: Dp = 72.dp,
    val DP_76: Dp = 76.dp,
    val DP_80: Dp = 80.dp,
    val DP_84: Dp = 84.dp,
    val DP_88: Dp = 88.dp,
    val DP_92: Dp = 92.dp,
    val DP_96: Dp = 96.dp,
    val DP_100: Dp = 100.dp,
    val DP_104: Dp = 104.dp,
    val DP_108: Dp = 108.dp,
    val DP_112: Dp = 112.dp,
    val DP_116: Dp = 116.dp,
    val DP_120: Dp = 120.dp,
    val DP_124: Dp = 124.dp,
    val DP_128: Dp = 128.dp,
    val DP_132: Dp = 132.dp,
    val DP_136: Dp = 136.dp,
    val DP_140: Dp = 140.dp,
    val DP_144: Dp = 144.dp,
    val DP_148: Dp = 148.dp,
    val DP_152: Dp = 152.dp,
    val DP_156: Dp = 156.dp,
    val DP_160: Dp = 160.dp,
    val DP_164: Dp = 164.dp,
    val DP_168: Dp = 168.dp,
    val DP_172: Dp = 172.dp,
    val DP_176: Dp = 176.dp,
    val DP_180: Dp = 180.dp,
    val DP_184: Dp = 184.dp,
    val DP_188: Dp = 188.dp,
    val DP_192: Dp = 192.dp,
    val DP_196: Dp = 196.dp,
    val DP_200: Dp = 200.dp
)

val dimens = Dimens()

val LocalDimens = compositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current
