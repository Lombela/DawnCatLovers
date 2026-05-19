package com.dawn.catlovers.feature.breeds.uistate

import com.dawn.catlovers.core.model.CatBreed

data class FavoritesUiState(
    val favoriteBreeds: List<CatBreed> = emptyList(),
    val suggestedBreeds: List<CatBreed> = emptyList(),
) {
    val favoriteCount: Int = favoriteBreeds.size
    val heroBreed: CatBreed? = favoriteBreeds.firstOrNull()
    val alsoSavedBreeds: List<CatBreed> = favoriteBreeds.drop(1)
}
