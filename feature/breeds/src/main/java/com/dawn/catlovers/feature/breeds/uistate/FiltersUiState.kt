package com.dawn.catlovers.feature.breeds.uistate

import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength

data class FiltersUiState(
    val filters: BreedFilters = DefaultFilters,
    val matches: List<CatBreed> = emptyList(),
    val origins: List<String> = emptyList(),
)

internal val DefaultFilters = BreedFilters(
    coatLength = CoatLength.Short,
    hypoallergenicOnly = true,
    minEnergy = 4,
)
