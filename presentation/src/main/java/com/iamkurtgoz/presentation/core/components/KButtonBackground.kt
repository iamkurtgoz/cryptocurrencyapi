package com.iamkurtgoz.presentation.core.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.core.animation.KLottieView
import com.iamkurtgoz.presentation.theme.Blue
import com.iamkurtgoz.presentation.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Composable
fun KButtonBackground(
    rootModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge.copy(
        fontWeight = FontWeight.Medium
    ),
    shape: Shape = MaterialTheme.shapes.small,
    containerColor: Color = Blue,
    contentColor: Color = Color.White,
    disabledContainerColor: Color = Color.Gray,
    disabledContentColor: Color = Color.DarkGray,
    isFullWidth: Boolean = true,
    isUpperCase: Boolean = false,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Button(
            onClick = onClick,
            modifier = rootModifier
                .fillMaxWidthStatus(active = isFullWidth),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor
            ),
            contentPadding = PaddingValues(0.dp),
            shape = shape,
            enabled = isEnabled && !isLoading
        ) {
            if (isLoading) {
                KLottieView(
                    res = R.raw.anim_loading,
                    reverseOnRepeat = true,
                    modifier = Modifier.size(MaterialTheme.dimens.DP_32)
                )
            } else {
                Text(
                    text = if (isUpperCase) text.uppercase() else text,
                    style = textStyle,
                    color = if (isEnabled) contentColor else disabledContentColor,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = textModifier
                        .align(CenterVertically)
                )
            }
        }
    }
}
