package com.iamkurtgoz.presentation.features.coin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.theme.dimens

@Composable
fun SearchViewTextField(value: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.dimens.DP_16)
            .padding(top = MaterialTheme.dimens.DP_16)
            .fillMaxWidth()
            .clip(RoundedCornerShape(MaterialTheme.dimens.DP_8))
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(MaterialTheme.dimens.DP_0))
                .height(MaterialTheme.dimens.DP_48)
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "image",
                        tint = Color.Blue
                    )
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value == "") {
                            Text(
                                stringResource(id = R.string.search)
                            )
                        }
                        innerTextField()
                    }
                    if (value != "") {
                        IconButton(
                            onClick = {
                                onValueChange.invoke("")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "image",
                                tint = Color.Blue
                            )
                        }
                    }
                }
            }
        )
    }
}
