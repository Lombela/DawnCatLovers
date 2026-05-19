package com.dawn.catlovers.feature.breeds.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    dark: Boolean = false,
) {
    IconCircleButton(
        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
        contentDescription = if (isFavorite) "Remove favorite" else "Add favorite",
        onClick = onClick,
        selected = isFavorite,
        dark = dark,
        modifier = modifier,
    )
}
