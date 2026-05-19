package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
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
    buttonSize: Dp = 34.dp,
    iconSize: Dp = 20.dp,
) {
    val background = when {
        selected -> Color.White.copy(alpha = 0.92f)
        dark -> CatLoversColors.Eternity.copy(alpha = 0.42f)
        else -> Color.Transparent
    }
    Box(
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = if (dark) Color.White else CatLoversColors.Zeus,
            modifier = Modifier.size(iconSize),
        )
    }
}
