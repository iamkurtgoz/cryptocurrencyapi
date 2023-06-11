package com.iamkurtgoz.presentation.core.animation

import android.content.res.Configuration
import android.graphics.Typeface
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.LottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieComposition
import com.iamkurtgoz.cryptocurrencyapi.presentation.R

@Composable
fun KLottieView(
    modifier: Modifier = Modifier,
    @RawRes res: Int,
    isPlaying: Boolean = true,
    restartOnPlay: Boolean = true,
    clipSpec: LottieClipSpec? = null,
    speed: Float = 1f,
    iterations: Int = LottieConstants.IterateForever,
    outlineMasksAndMattes: Boolean = false,
    applyOpacityToLayers: Boolean = false,
    enableMergePaths: Boolean = false,
    renderMode: RenderMode = RenderMode.AUTOMATIC,
    reverseOnRepeat: Boolean = false,
    maintainOriginalImageBounds: Boolean = false,
    dynamicProperties: LottieDynamicProperties? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    clipToCompositionBounds: Boolean = true,
    fontMap: Map<String, Typeface>? = null
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        isPlaying = isPlaying,
        restartOnPlay = restartOnPlay,
        clipSpec = clipSpec,
        speed = speed,
        iterations = iterations,
        outlineMasksAndMattes = outlineMasksAndMattes,
        applyOpacityToLayers = applyOpacityToLayers,
        enableMergePaths = enableMergePaths,
        renderMode = renderMode,
        reverseOnRepeat = reverseOnRepeat,
        maintainOriginalImageBounds = maintainOriginalImageBounds,
        dynamicProperties = dynamicProperties,
        alignment = alignment,
        contentScale = contentScale,
        clipToCompositionBounds = clipToCompositionBounds,
        fontMap = fontMap
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun KLottieViewPreview() {
    KLottieView(
        modifier = Modifier.size(75.dp),
        res = R.raw.anim_loading
    )
}
