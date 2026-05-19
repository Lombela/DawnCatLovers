package com.dawn.catlovers.feature.breeds.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.catlovers.core.domain.CoroutineDispatchers
import com.dawn.catlovers.core.domain.usecase.ObserveBreedsUseCase
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.model.Lifestyle
import com.dawn.catlovers.feature.breeds.uistate.DefaultFilters
import com.dawn.catlovers.feature.breeds.uistate.FiltersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class FiltersViewModel @Inject constructor(
    private val observeBreeds: ObserveBreedsUseCase,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {
    private val filters = MutableStateFlow(DefaultFilters)

    private val matches = filters
        .flatMapLatest { activeFilters ->
            observeBreeds(activeFilters)
        }

    private val allBreeds = observeBreeds()

    val uiState: StateFlow<FiltersUiState> = combine(
        filters,
        matches,
        allBreeds,
    ) { filters, matches, allBreeds ->
        FiltersUiState(
            filters = filters,
            matches = matches,
            origins = allBreeds
                .map { it.origin }
                .filter { it.isNotBlank() }
                .distinct()
                .sorted(),
        )
    }
        .flowOn(dispatchers.default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000),
            initialValue = FiltersUiState(),
        )

    fun setQuery(query: String) {
        filters.update { it.copy(query = query) }
    }

    fun setCoatLength(coatLength: CoatLength?) {
        filters.update { it.copy(coatLength = coatLength) }
    }

    fun setHypoallergenic(selected: Boolean) {
        filters.update { it.copy(hypoallergenicOnly = selected) }
    }

    fun setMinEnergy(minEnergy: Int?) {
        filters.update { it.copy(minEnergy = minEnergy) }
    }

    fun setOrigin(origin: String?) {
        filters.update { it.copy(origin = origin) }
    }

    fun toggleLifestyle(lifestyle: Lifestyle) {
        filters.update {
            val current = it.lifestyles
            it.copy(
                lifestyles = if (lifestyle in current) {
                    current - lifestyle
                } else {
                    current + lifestyle
                },
            )
        }
    }

    fun clearAll() {
        filters.value = BreedFilters()
    }
}
