package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun GradientScrim(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                0.35f to Color.Transparent,
                1f to CatLoversColors.Eternity.copy(alpha = 0.72f),
            ),
        ),
    )
}
