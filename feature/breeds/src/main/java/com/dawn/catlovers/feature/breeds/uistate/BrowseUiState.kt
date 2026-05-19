package com.dawn.catlovers.feature.breeds.uistate

import androidx.annotation.StringRes
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R

data class BrowseUiState(
    val breeds: List<CatBreed> = emptyList(),
    val selectedFilter: QuickFilter = QuickFilter.All,
    val isRefreshing: Boolean = false,
    @StringRes val syncMessageResId: Int? = null,
) {
    val heroBreed: CatBreed? = breeds.firstOrNull()
}

enum class QuickFilter(@StringRes val labelResId: Int) {
    All(R.string.quick_filter_all),
    Hypoallergenic(R.string.quick_filter_hypoallergenic),
    Family(R.string.quick_filter_family),
    Indoor(R.string.quick_filter_indoor),
    Calm(R.string.quick_filter_calm),
}
