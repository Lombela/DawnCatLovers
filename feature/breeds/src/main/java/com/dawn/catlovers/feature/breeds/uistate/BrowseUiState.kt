package com.dawn.catlovers.feature.breeds.uistate

import com.dawn.catlovers.core.model.CatBreed

data class BrowseUiState(
    val breeds: List<CatBreed> = emptyList(),
    val selectedFilter: QuickFilter = QuickFilter.All,
    val isRefreshing: Boolean = false,
    val syncMessage: String? = null,
) {
    val heroBreed: CatBreed? = breeds.firstOrNull()
}

enum class QuickFilter(val label: String) {
    All("All"),
    Hypoallergenic("Hypoallergenic"),
    Family("Family-friendly"),
    Indoor("Indoor"),
    Calm("Calm"),
}
