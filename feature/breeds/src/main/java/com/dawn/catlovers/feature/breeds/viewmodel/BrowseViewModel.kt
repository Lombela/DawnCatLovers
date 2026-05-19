package com.dawn.catlovers.feature.breeds.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.catlovers.core.domain.CoroutineDispatchers
import com.dawn.catlovers.core.domain.usecase.ObserveBreedsUseCase
import com.dawn.catlovers.core.domain.usecase.RefreshBreedsUseCase
import com.dawn.catlovers.core.domain.usecase.SetFavoriteUseCase
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.Lifestyle
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.uistate.BrowseUiState
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class BrowseViewModel @Inject constructor(
    private val observeBreeds: ObserveBreedsUseCase,
    private val refreshBreeds: RefreshBreedsUseCase,
    private val setFavorite: SetFavoriteUseCase,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {
    private val selectedFilter = MutableStateFlow(QuickFilter.All)
    private val refreshing = MutableStateFlow(false)
    private val syncMessageResId = MutableStateFlow<Int?>(null)

    private val breeds = selectedFilter
        .flatMapLatest { observeBreeds(it.toFilters()) }

    val uiState: StateFlow<BrowseUiState> = combine(
        breeds,
        selectedFilter,
        refreshing,
        syncMessageResId,
    ) { breeds, selectedFilter, refreshing, syncMessageResId ->
        BrowseUiState(
            breeds = breeds,
            selectedFilter = selectedFilter,
            isRefreshing = refreshing,
            syncMessageResId = syncMessageResId,
        )
    }
        .flowOn(dispatchers.default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BrowseUiState(),
        )

    init {
        refresh()
    }

    fun selectFilter(filter: QuickFilter) {
        selectedFilter.value = filter
    }

    fun refresh() {
        viewModelScope.launch {
            refreshing.value = true
            refreshBreeds()
                .onSuccess { syncMessageResId.value = null }
                .onFailure { syncMessageResId.value = R.string.browse_sync_failed }
            refreshing.value = false
        }
    }

    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            setFavorite(breed.id, !breed.isFavorite)
        }
    }

    private fun QuickFilter.toFilters(): BreedFilters = when (this) {
        QuickFilter.All -> BreedFilters()
        QuickFilter.Hypoallergenic -> BreedFilters(hypoallergenicOnly = true)
        QuickFilter.Family -> BreedFilters(lifestyles = setOf(Lifestyle.Family))
        QuickFilter.Indoor -> BreedFilters(lifestyles = setOf(Lifestyle.Indoor))
        QuickFilter.Calm -> BreedFilters(query = "calm")
    }
}
