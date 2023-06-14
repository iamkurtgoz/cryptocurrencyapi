package com.iamkurtgoz.presentation.features.coin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.presentation.theme.AppShapes
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.dimens
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CoinCard(index: Int, item: CoinUIModel, onClick: (CoinUIModel) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick.invoke(item) }
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimens.DP_16)
            .padding(top = if (index == 0) MaterialTheme.dimens.DP_16 else MaterialTheme.dimens.DP_8)
            .padding(vertical = MaterialTheme.dimens.DP_4),
        shape = AppShapes.extraSmall,
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.dimens.DP_2
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = MaterialTheme.dimens.DP_4)
                .padding(horizontal = MaterialTheme.dimens.DP_8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GlideImage(
                imageModel = { item.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .size(MaterialTheme.dimens.DP_40)
            )

            Text(
                text = item.name ?: "",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .padding(start = MaterialTheme.dimens.DP_16)
                    .weight(1f),
                maxLines = 1,
                color = ColorTextPrimary()
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = item.formatHigh24() ?: "",
                    style = MaterialTheme.typography.labelSmall.copy(
                        textAlign = TextAlign.Center,
                        color = Color.Green
                    ),
                    modifier = Modifier
                        .padding(start = MaterialTheme.dimens.DP_16)
                )

                Text(
                    text = item.formatLow24() ?: "",
                    style = MaterialTheme.typography.labelSmall.copy(
                        textAlign = TextAlign.Center,
                        color = Color.Red
                    ),
                    modifier = Modifier
                        .padding(start = MaterialTheme.dimens.DP_16)
                )
            }
        }
    }
}
