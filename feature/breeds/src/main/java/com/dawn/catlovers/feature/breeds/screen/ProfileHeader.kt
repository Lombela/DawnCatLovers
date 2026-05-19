package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.component.FilterSlidersIcon
import com.dawn.catlovers.feature.breeds.component.IconCircleButton

@Composable
internal fun ProfileHeader(
    onOpenFilters: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(start = 7.dp, end = 7.dp, top = 7.dp),
    ) {
        Text(
            text = "Profile",
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 16.sp,
                lineHeight = 18.sp,
            ),
            modifier = Modifier.align(Alignment.Center),
        )
        IconCircleButton(
            imageVector = FilterSlidersIcon,
            contentDescription = "Open filters",
            onClick = onOpenFilters,
            buttonSize = 33.dp,
            iconSize = 20.dp,
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}
