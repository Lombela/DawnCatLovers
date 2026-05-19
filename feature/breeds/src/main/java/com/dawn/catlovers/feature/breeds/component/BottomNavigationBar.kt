package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.feature.breeds.R

@Composable
internal fun BottomNavigationBar(
    selectedDestination: BottomNavDestination = BottomNavDestination.Browse,
    onBrowseClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Surface(
        color = CatLoversColors.SpringWood,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
        ) {
            HorizontalDivider(color = CatLoversColors.Swirl)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(59.dp)
                    .padding(top = 11.dp, bottom = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BottomNavItem(
                    icon = Icons.Rounded.Home,
                    label = stringResource(R.string.bottom_nav_browse),
                    selected = selectedDestination == BottomNavDestination.Browse,
                    onClick = onBrowseClick,
                    modifier = Modifier.weight(1f),
                )
                BottomNavItem(
                    icon = if (selectedDestination == BottomNavDestination.Favorites) {
                        Icons.Rounded.Favorite
                    } else {
                        Icons.Rounded.FavoriteBorder
                    },
                    label = stringResource(R.string.bottom_nav_favorites),
                    selected = selectedDestination == BottomNavDestination.Favorites,
                    onClick = onFavoritesClick,
                    modifier = Modifier.weight(1f),
                )
                BottomNavItem(
                    icon = Icons.Rounded.LocationOn,
                    label = stringResource(R.string.bottom_nav_profile),
                    selected = selectedDestination == BottomNavDestination.Profile,
                    onClick = onProfileClick,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

internal enum class BottomNavDestination {
    Browse,
    Favorites,
    Profile,
}

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    Column(
        modifier = modifier.clickable(
            enabled = !selected,
            role = Role.Tab,
            onClick = onClick,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Box(
            modifier = Modifier
                .width(53.dp)
                .height(27.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(if (selected) CatLoversColors.SelectedChip else Color.Transparent),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (selected) CatLoversColors.Zeus else CatLoversColors.SoyaBean,
                modifier = Modifier.size(18.dp),
            )
        }
        Text(
            text = label,
            color = if (selected) CatLoversColors.Zeus else CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, lineHeight = 12.sp),
        )
    }
}
