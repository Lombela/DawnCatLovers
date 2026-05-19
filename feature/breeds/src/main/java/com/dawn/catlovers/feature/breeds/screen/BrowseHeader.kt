package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.component.FilterSlidersIcon
import com.dawn.catlovers.feature.breeds.component.IconCircleButton

@Composable
internal fun BrowseHeader(
    isRefreshing: Boolean,
    onOpenFilters: () -> Unit,
    onRefresh: () -> Unit,
) {
    val titlePrefix = stringResource(R.string.browse_title_prefix)
    val titleEmphasis = stringResource(R.string.browse_title_emphasis)
    val titleSuffix = stringResource(R.string.browse_title_suffix)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp),
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
                    contentDescription = stringResource(R.string.content_description_search),
                    onClick = onOpenFilters,
                    buttonSize = 33.dp,
                    iconSize = 20.dp,
                )
                IconCircleButton(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = stringResource(R.string.content_description_refresh_breeds),
                    onClick = onRefresh,
                    buttonSize = 33.dp,
                    iconSize = 20.dp,
                )
            }
        }
        Column(
            modifier = Modifier.padding(start = 23.dp, end = 23.dp, top = 3.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(R.string.browse_eyebrow),
                color = CatLoversColors.StTropaz,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    letterSpacing = 0.5.sp,
                ),
            )
            Text(
                text = buildAnnotatedString {
                    append(titlePrefix)
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(titleEmphasis)
                    }
                    append(" ")
                    append(titleSuffix)
                },
                color = CatLoversColors.Zeus,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 27.sp,
                    lineHeight = 30.sp,
                    letterSpacing = 0.sp,
                ),
            )
        }
        if (isRefreshing) {
            Row(
                modifier = Modifier.padding(start = 23.dp, end = 23.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CircularProgressIndicator(modifier = Modifier.size(14.dp), strokeWidth = 2.dp)
                Text(
                    text = stringResource(R.string.browse_syncing),
                    color = CatLoversColors.SoyaBean,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}
