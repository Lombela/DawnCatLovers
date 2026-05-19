package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter

@Composable
internal fun QuickFilterRow(
    selected: QuickFilter,
    onSelectFilter: (QuickFilter) -> Unit,
) {
    LazyRow(
        modifier = Modifier.height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(start = 13.dp, end = 13.dp),
    ) {
        items(QuickFilter.entries.size) { index ->
            val filter = QuickFilter.entries[index]
            BrowseFilterChip(
                label = filter.label,
                selected = selected == filter,
                onClick = { onSelectFilter(filter) },
            )
        }
    }
}

@Composable
private fun BrowseFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = if (selected) CatLoversColors.Cedar else CatLoversColors.Zeus
    Row(
        modifier = Modifier
            .height(27.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) CatLoversColors.SelectedChip else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (selected) Color.Transparent else CatLoversColors.Swirl,
                shape = RoundedCornerShape(8.dp),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        if (selected) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(13.dp),
            )
        }
        Text(
            text = label,
            color = contentColor,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 11.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
