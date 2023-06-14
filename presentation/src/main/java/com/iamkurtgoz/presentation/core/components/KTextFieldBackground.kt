package com.iamkurtgoz.presentation.core.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.iamkurtgoz.presentation.theme.Blue
import com.iamkurtgoz.presentation.theme.ColorTextFieldBackgroundGray
import com.iamkurtgoz.presentation.theme.ColorTextFieldPlaceholder
import com.iamkurtgoz.presentation.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Composable
fun KTextFieldBackground(
    rootModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: MutableState<TextFieldValue>,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    placeholder: String = "",
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    placeholderColor: Color = ColorTextFieldPlaceholder(),
    @DrawableRes headerImage: Int? = null,
    headerImageSize: Dp = MaterialTheme.dimens.DP_16,
    headerImageColorFilter: ColorFilter = ColorFilter.tint(Blue),
    shape: Shape = MaterialTheme.shapes.small,
    borderStroke: BorderStroke? = null,
    borderShape: Shape? = null,
    backgroundColor: Color = ColorTextFieldBackgroundGray(),
    isFullWidth: Boolean = true,
    textAlign: TextAlign? = null,
    lineLimit: Int = Int.MAX_VALUE,
    limit: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        val borderModifier: Modifier? = if (borderStroke != null && borderShape != null) {
            Modifier.border(border = borderStroke, shape = borderShape)
        } else {
            null
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = rootModifier
                .clip(shape)
                .then(borderModifier ?: Modifier)
                .background(backgroundColor)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidthStatus(active = isFullWidth)
            ) {
                if (headerImage != null) {
                    Image(
                        painter = painterResource(id = headerImage),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = MaterialTheme.dimens.DP_8)
                            .size(headerImageSize),
                        colorFilter = headerImageColorFilter
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidthStatus(active = isFullWidth)
                ) {
                    BasicTextField(
                        value = text.value,
                        onValueChange = {
                            if (it.text.length <= limit) text.value = it
                        },
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        cursorBrush = SolidColor(Blue),
                        singleLine = lineLimit == 1,
                        visualTransformation = visualTransformation,
                        textStyle = textStyle.copy(
                            color = textColor
                        ),
                        modifier = textModifier
                            .fillMaxWidthStatus(active = isFullWidth)
                            .background(Color.Transparent)
                            .padding(horizontal = MaterialTheme.dimens.DP_8)
                            .padding(vertical = MaterialTheme.dimens.DP_8)
                    )

                    if (text.value.text.isEmpty() && placeholder.isNotEmpty()) {
                        Text(
                            text = placeholder,
                            color = placeholderColor,
                            style = textStyle,
                            textAlign = textAlign,
                            maxLines = lineLimit,
                            overflow = TextOverflow.Ellipsis,
                            modifier = textModifier
                                .fillMaxWidthStatus(active = isFullWidth)
                                .padding(horizontal = MaterialTheme.dimens.DP_8)
                                .padding(vertical = MaterialTheme.dimens.DP_8)
                        )
                    }
                }
            }
        }
    }
}
