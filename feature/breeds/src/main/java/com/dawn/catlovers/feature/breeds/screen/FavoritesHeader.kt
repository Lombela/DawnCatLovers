package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.component.FilterSlidersIcon
import com.dawn.catlovers.feature.breeds.component.IconCircleButton

@Composable
internal fun FavoritesHeader(
    favoriteCount: Int,
    onOpenFilters: () -> Unit,
    onSearch: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(if (favoriteCount > 0) 172.dp else 146.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(start = 7.dp, end = 7.dp, top = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconCircleButton(
                imageVector = FilterSlidersIcon,
                contentDescription = stringResource(R.string.content_description_open_filters),
                onClick = onOpenFilters,
                buttonSize = 33.dp,
                iconSize = 20.dp,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                IconCircleButton(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(R.string.content_description_search_breeds),
                    onClick = onSearch,
                    buttonSize = 33.dp,
                    iconSize = 20.dp,
                )
                if (favoriteCount > 0) {
                    IconCircleButton(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = stringResource(R.string.content_description_share_favorites),
                        onClick = onShare,
                        buttonSize = 33.dp,
                        iconSize = 18.dp,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(start = 23.dp, end = 23.dp, top = 3.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(R.string.favorites_eyebrow),
                color = CatLoversColors.StTropaz,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    letterSpacing = 0.5.sp,
                ),
            )
            Text(
                text = stringResource(R.string.favorites_title),
                color = CatLoversColors.Zeus,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 27.sp,
                    lineHeight = 30.sp,
                    fontStyle = FontStyle.Italic,
                    letterSpacing = 0.sp,
                ),
            )
            if (favoriteCount > 0) {
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = null,
                        tint = CatLoversColors.Zeus,
                        modifier = Modifier.size(14.dp),
                    )
                    Text(
                        text = favoriteCount.toString(),
                        color = CatLoversColors.Zeus,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 12.5.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                    Text(
                        text = stringResource(R.string.favorites_saved_shortlist_suffix),
                        color = CatLoversColors.SoyaBean,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 11.5.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                    )
                }
            }
        }
    }
}
