package com.dawn.catlovers.feature.breeds.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun BottomNavigationBar(modifier: Modifier = Modifier) {
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
                    .padding(horizontal = 12.dp, vertical = 7.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BottomNavItem(icon = Icons.Rounded.Home, label = "Browse", selected = true)
                BottomNavItem(icon = Icons.Rounded.FavoriteBorder, label = "Favorites")
                BottomNavItem(icon = PawIcon, label = "Quiz")
                BottomNavItem(icon = Icons.Rounded.LocationOn, label = "Profile")
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Box(
            modifier = Modifier
                .width(56.dp)
                .height(28.dp)
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

private val PawIcon: ImageVector = ImageVector.Builder(
    name = "Paw",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(fill = SolidColor(Color.Black)) {
        moveTo(4.5f, 12f)
        curveTo(5.88f, 12f, 7f, 10.88f, 7f, 9.5f)
        curveTo(7f, 8.12f, 5.88f, 7f, 4.5f, 7f)
        curveTo(3.12f, 7f, 2f, 8.12f, 2f, 9.5f)
        curveTo(2f, 10.88f, 3.12f, 12f, 4.5f, 12f)
        close()
        moveTo(9f, 9f)
        curveTo(10.38f, 9f, 11.5f, 7.88f, 11.5f, 6.5f)
        curveTo(11.5f, 5.12f, 10.38f, 4f, 9f, 4f)
        curveTo(7.62f, 4f, 6.5f, 5.12f, 6.5f, 6.5f)
        curveTo(6.5f, 7.88f, 7.62f, 9f, 9f, 9f)
        close()
        moveTo(15f, 9f)
        curveTo(16.38f, 9f, 17.5f, 7.88f, 17.5f, 6.5f)
        curveTo(17.5f, 5.12f, 16.38f, 4f, 15f, 4f)
        curveTo(13.62f, 4f, 12.5f, 5.12f, 12.5f, 6.5f)
        curveTo(12.5f, 7.88f, 13.62f, 9f, 15f, 9f)
        close()
        moveTo(19.5f, 7f)
        curveTo(18.12f, 7f, 17f, 8.12f, 17f, 9.5f)
        curveTo(17f, 10.88f, 18.12f, 12f, 19.5f, 12f)
        curveTo(20.88f, 12f, 22f, 10.88f, 22f, 9.5f)
        curveTo(22f, 8.12f, 20.88f, 7f, 19.5f, 7f)
        close()
        moveTo(17.34f, 14.86f)
        curveTo(16.47f, 13.84f, 15.74f, 12.97f, 14.86f, 11.95f)
        curveTo(14.4f, 11.41f, 13.81f, 10.87f, 13.11f, 10.63f)
        curveTo(12.76f, 10.51f, 12.38f, 10.5f, 12f, 10.5f)
        curveTo(11.62f, 10.5f, 11.24f, 10.51f, 10.89f, 10.63f)
        curveTo(10.19f, 10.87f, 9.6f, 11.41f, 9.14f, 11.95f)
        curveTo(8.26f, 12.97f, 7.53f, 13.84f, 6.66f, 14.86f)
        curveTo(5.35f, 16.17f, 3.74f, 17.62f, 4.04f, 19.65f)
        curveTo(4.33f, 20.67f, 5.17f, 21.68f, 6.97f, 21.68f)
        curveTo(8.28f, 21.68f, 10.24f, 20.7f, 12f, 20.7f)
        curveTo(13.76f, 20.7f, 15.72f, 21.68f, 17.03f, 21.68f)
        curveTo(18.83f, 21.68f, 19.67f, 20.67f, 19.96f, 19.65f)
        curveTo(20.26f, 17.62f, 18.65f, 16.17f, 17.34f, 14.86f)
        close()
    }
}.build()
