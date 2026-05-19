package com.dawn.catlovers.feature.breeds.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        color = CatLoversColors.SoyaBean,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier,
    )
}
