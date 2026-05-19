package com.dawn.catlovers.feature.breeds.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    dark: Boolean = false,
    buttonSize: Dp = 34.dp,
    iconSize: Dp = 20.dp,
) {
    IconCircleButton(
        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
        contentDescription = if (isFavorite) "Remove favorite" else "Add favorite",
        onClick = onClick,
        selected = isFavorite,
        dark = dark,
        modifier = modifier,
        buttonSize = buttonSize,
        iconSize = iconSize,
    )
}
