package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun BrowseSurpriseButton(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = CatLoversColors.TropicalBlue,
        shadowElevation = 6.dp,
        modifier = Modifier
            .width(121.dp)
            .height(47.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = CatLoversColors.Midnight,
                modifier = Modifier.size(17.dp),
            )
            Text(
                text = "Surprise me",
                color = CatLoversColors.Midnight,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp, lineHeight = 12.sp),
            )
        }
    }
}
