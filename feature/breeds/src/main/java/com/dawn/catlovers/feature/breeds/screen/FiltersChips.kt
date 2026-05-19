package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.model.Lifestyle
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.labelText

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ActiveFiltersSection(
    filters: BreedFilters,
    onClearAll: () -> Unit,
    onSetCoatLength: (CoatLength?) -> Unit,
    onSetHypoallergenic: (Boolean) -> Unit,
    onSetMinEnergy: (Int?) -> Unit,
    onSetOrigin: (String?) -> Unit,
    onToggleLifestyle: (Lifestyle) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.filters_title).uppercase(),
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    letterSpacing = 0.7.sp,
                ),
            )
            Text(
                text = if (filters.activeCount > 0) {
                    stringResource(R.string.active_filter_clear_all_count, filters.activeCount)
                } else {
                    stringResource(R.string.active_filter_clear_all)
                },
                color = CatLoversColors.StTropaz,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 11.sp),
                modifier = Modifier.clickable(onClick = onClearAll),
            )
        }
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            filters.coatLength?.let { coat ->
                FilterChip(
                    label = stringResource(R.string.active_filter_coat, coat.labelText()),
                    selected = true,
                    onClick = { onSetCoatLength(null) },
                )
            }
            if (filters.hypoallergenicOnly) {
                FilterChip(
                    label = stringResource(R.string.active_filter_hypoallergenic),
                    selected = true,
                    onClick = { onSetHypoallergenic(false) },
                )
            }
            filters.minEnergy?.let { energy ->
                FilterChip(
                    label = stringResource(R.string.active_filter_energy, energy),
                    selected = true,
                    onClick = { onSetMinEnergy(null) },
                )
            }
            filters.origin?.takeIf(String::isNotBlank)?.let { origin ->
                FilterChip(
                    label = origin,
                    selected = true,
                    onClick = { onSetOrigin(null) },
                )
            }
            filters.lifestyles.forEach { lifestyle ->
                FilterChip(
                    label = lifestyle.labelText(),
                    selected = true,
                    onClick = { onToggleLifestyle(lifestyle) },
                )
            }
            FilterChip(
                label = stringResource(R.string.add_filter),
                selected = false,
                showSelectedIcon = false,
                onClick = { },
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun FilterSection(
    title: String,
    contentHeight: Dp? = null,
    content: @Composable FlowRowScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 13.dp),
    ) {
        Text(
            text = title,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 11.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 7.dp),
        )
        val contentModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .let { baseModifier ->
                    if (contentHeight == null) {
                        baseModifier
                    } else {
                        baseModifier
                            .height(contentHeight)
                            .verticalScroll(rememberScrollState())
                    }
                }
        FlowRow(
            modifier = contentModifier,
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            content = content,
        )
    }
}

@Composable
internal fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showSelectedIcon: Boolean = true,
) {
    Row(
        modifier = modifier
            .height(27.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) CatLoversColors.SelectedChip else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (selected) Color.Transparent else CatLoversColors.Swirl,
                shape = RoundedCornerShape(8.dp),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        if (selected && showSelectedIcon) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = CatLoversColors.Zeus,
                modifier = Modifier.size(13.dp),
            )
        }
        Text(
            text = label,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 11.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
