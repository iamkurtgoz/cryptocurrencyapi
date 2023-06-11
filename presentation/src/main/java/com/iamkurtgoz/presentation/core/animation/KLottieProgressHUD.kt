package com.iamkurtgoz.presentation.core.animation

import android.content.res.Configuration
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.theme.dimens

@Composable
fun KLottieProgressHUD(
    modifier: Modifier = Modifier,
    size: Dp = MaterialTheme.dimens.DP_56,
    modifierLottieAnimation: Modifier = Modifier,
    @RawRes res: Int = R.raw.anim_loading
) {
    Dialog(
        onDismissRequest = {
        }
    ) {
        Surface(
            modifier = modifier.size(size),
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(MaterialTheme.dimens.DP_16),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(MaterialTheme.dimens.DP_8),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                KLottieView(
                    modifier = modifierLottieAnimation,
                    res = res
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun KLottieProgressHUDPreview() {
    KLottieProgressHUD(
        size = MaterialTheme.dimens.DP_56
    )
}
