package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun IconCircleButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    dark: Boolean = false,
) {
    val background = when {
        selected -> Color.White.copy(alpha = 0.92f)
        dark -> CatLoversColors.Eternity.copy(alpha = 0.42f)
        else -> Color.Transparent
    }
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(background),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = if (dark) Color.White else CatLoversColors.Zeus,
            modifier = Modifier.size(20.dp),
        )
    }
}
