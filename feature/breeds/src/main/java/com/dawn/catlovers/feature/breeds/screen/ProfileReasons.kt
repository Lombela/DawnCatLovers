package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.component.FilterSlidersIcon
import com.dawn.catlovers.feature.breeds.component.StarBadgeIcon

@Composable
internal fun WhySignInSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 34.dp, top = 26.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "WHY SIGN IN?",
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 10.sp,
                lineHeight = 10.sp,
                letterSpacing = 0.7.sp,
            ),
        )
        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
            SignInReasonItem(
                icon = Icons.Rounded.Favorite,
                text = "Save favorites across devices",
            )
            SignInReasonItem(
                icon = FilterSlidersIcon,
                text = "Keep personal notes with every breed",
            )
            SignInReasonItem(
                icon = StarBadgeIcon,
                text = "Restore your shortlist on a new device",
            )
        }
    }
}

@Composable
private fun SignInReasonItem(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        Box(
            modifier = Modifier
                .size(27.dp)
                .clip(CircleShape)
                .background(CatLoversColors.TropicalBlue),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CatLoversColors.Midnight,
                modifier = Modifier.size(15.dp),
            )
        }
        Text(
            text = text,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.5.sp,
                lineHeight = 17.sp,
            ),
        )
    }
}
