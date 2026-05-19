package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.uistate.FavoritesUiState

@Composable
internal fun SavedFavoritesContent(
    uiState: FavoritesUiState,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    val heroBreed = uiState.heroBreed ?: return
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 104.dp),
    ) {
        item {
            FavoritesControls(
                modifier = Modifier.padding(start = 14.dp, end = 14.dp, bottom = 14.dp),
            )
        }
        item {
            FavoriteHeroCard(
                breed = heroBreed,
                onOpenBreed = onOpenBreed,
                onToggleFavorite = onToggleFavorite,
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .height(274.dp),
            )
        }
        if (uiState.alsoSavedBreeds.isNotEmpty()) {
            item {
                AlsoSavedHeader(
                    count = uiState.alsoSavedBreeds.size,
                    modifier = Modifier.padding(top = 18.dp),
                )
            }
            items(
                items = uiState.alsoSavedBreeds,
                key = CatBreed::id,
            ) { breed ->
                FavoriteListItem(
                    breed = breed,
                    onOpenBreed = onOpenBreed,
                    onToggleFavorite = onToggleFavorite,
                    modifier = Modifier.padding(horizontal = 7.dp),
                )
            }
        }
        item {
            CompareShortlistBanner(
                modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 20.dp),
            )
        }
    }
}

@Composable
private fun FavoritesControls(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(CatLoversColors.PearlBush)
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SegmentLabel(
                text = stringResource(R.string.favorites_segment_saved),
                selected = true,
            )
            SegmentLabel(
                text = stringResource(R.string.favorites_segment_recently_viewed),
                selected = false,
            )
        }
        SortChip()
    }
}

@Composable
private fun SegmentLabel(
    text: String,
    selected: Boolean,
) {
    Box(
        modifier = Modifier
            .height(26.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) CatLoversColors.Linen else CatLoversColors.PearlBush)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if (selected) CatLoversColors.Zeus else CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.5.sp, lineHeight = 12.sp),
            maxLines = 1,
        )
    }
}

@Composable
private fun SortChip() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = CatLoversColors.SpringWood,
        shadowElevation = 2.dp,
        modifier = Modifier.height(32.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = stringResource(R.string.favorites_sort_name),
                color = CatLoversColors.Zeus,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.5.sp, lineHeight = 12.sp),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = CatLoversColors.Zeus,
                modifier = Modifier.size(13.dp),
            )
        }
    }
}

@Composable
private fun AlsoSavedHeader(
    count: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(33.dp)
            .padding(horizontal = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = stringResource(R.string.favorites_also_saved),
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp, lineHeight = 22.sp),
        )
        Text(
            text = pluralStringResource(R.plurals.breed_count, count, count),
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Normal,
            ),
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}

@Composable
private fun CompareShortlistBanner(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = CatLoversColors.Skeptic,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(androidx.compose.ui.graphics.Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = CatLoversColors.BottleGreen,
                    modifier = Modifier.size(20.dp),
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = stringResource(R.string.favorites_compare_shortlist),
                    color = CatLoversColors.BottleGreen,
                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.5.sp, lineHeight = 18.sp),
                )
                Text(
                    text = stringResource(R.string.favorites_compare_shortlist_body),
                    color = CatLoversColors.BottleGreen.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 10.5.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = CatLoversColors.BottleGreen,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}
