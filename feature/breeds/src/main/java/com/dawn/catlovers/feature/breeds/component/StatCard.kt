package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun StatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(CatLoversColors.SpringWood)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = label.uppercase(),
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = value,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
