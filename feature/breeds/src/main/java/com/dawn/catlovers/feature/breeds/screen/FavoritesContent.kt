package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.runtime.Composable
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.uistate.FavoritesUiState

@Composable
internal fun FavoritesContent(
    uiState: FavoritesUiState,
    onOpenBrowse: () -> Unit,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    if (uiState.favoriteBreeds.isEmpty()) {
        EmptyFavoritesContent(
            suggestedBreeds = uiState.suggestedBreeds,
            onOpenBrowse = onOpenBrowse,
            onOpenBreed = onOpenBreed,
            onToggleFavorite = onToggleFavorite,
        )
    } else {
        SavedFavoritesContent(
            uiState = uiState,
            onOpenBreed = onOpenBreed,
            onToggleFavorite = onToggleFavorite,
        )
    }
}
