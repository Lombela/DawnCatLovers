package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun TraitBar(
    label: String,
    value: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = label,
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.width(98.dp),
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(7.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (index < value) CatLoversColors.StTropaz else CatLoversColors.PearlBush),
                )
            }
        }
        Text(
            text = value.toString(),
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.width(16.dp),
        )
    }
}
