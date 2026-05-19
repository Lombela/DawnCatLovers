package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun CountryBadge(countryCode: String, modifier: Modifier = Modifier) {
    if (countryCode.isBlank()) return
    Box(
        modifier = modifier
            .height(13.dp)
            .defaultMinSize(minWidth = 18.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(CatLoversColors.PearlBush)
            .padding(horizontal = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = countryCode.uppercase(),
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 8.sp,
                lineHeight = 8.sp,
                letterSpacing = 0.4.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}
