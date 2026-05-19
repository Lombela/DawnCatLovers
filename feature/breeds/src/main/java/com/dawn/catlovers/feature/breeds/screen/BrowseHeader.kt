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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.component.IconCircleButton

@Composable
internal fun BrowseHeader(
    isRefreshing: Boolean,
    onOpenFilters: () -> Unit,
    onRefresh: () -> Unit,
) {
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
                contentDescription = "Open filters",
                onClick = onOpenFilters,
                buttonSize = 33.dp,
                iconSize = 20.dp,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                IconCircleButton(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    onClick = onOpenFilters,
                    buttonSize = 33.dp,
                    iconSize = 20.dp,
                )
                IconCircleButton(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "Refresh breeds",
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
                text = "CAT LOVER",
                color = CatLoversColors.StTropaz,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    letterSpacing = 0.5.sp,
                ),
            )
            Text(
                text = buildAnnotatedString {
                    append("Find your\n")
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("perfect")
                    }
                    append(" companion")
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
                    text = "Syncing TheCatAPI",
                    color = CatLoversColors.SoyaBean,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

private val FilterSlidersIcon: ImageVector = ImageVector.Builder(
    name = "FilterSliders",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(fill = SolidColor(Color.Black)) {
        moveTo(4f, 7f)
        horizontalLineTo(10f)
        verticalLineTo(5f)
        horizontalLineTo(12f)
        verticalLineTo(11f)
        horizontalLineTo(10f)
        verticalLineTo(9f)
        horizontalLineTo(4f)
        verticalLineTo(7f)
        close()
        moveTo(14f, 7f)
        horizontalLineTo(20f)
        verticalLineTo(9f)
        horizontalLineTo(14f)
        verticalLineTo(7f)
        close()
        moveTo(4f, 15f)
        horizontalLineTo(8f)
        verticalLineTo(13f)
        horizontalLineTo(10f)
        verticalLineTo(19f)
        horizontalLineTo(8f)
        verticalLineTo(17f)
        horizontalLineTo(4f)
        verticalLineTo(15f)
        close()
        moveTo(12f, 15f)
        horizontalLineTo(20f)
        verticalLineTo(17f)
        horizontalLineTo(12f)
        verticalLineTo(15f)
        close()
        moveTo(16f, 3f)
        horizontalLineTo(18f)
        verticalLineTo(5f)
        horizontalLineTo(20f)
        verticalLineTo(7f)
        horizontalLineTo(18f)
        verticalLineTo(11f)
        horizontalLineTo(16f)
        verticalLineTo(7f)
        horizontalLineTo(14f)
        verticalLineTo(5f)
        horizontalLineTo(16f)
        verticalLineTo(3f)
        close()
    }
}.build()
