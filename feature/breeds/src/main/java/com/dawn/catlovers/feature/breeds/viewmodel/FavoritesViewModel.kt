package com.dawn.catlovers.feature.breeds.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.catlovers.core.domain.CoroutineDispatchers
import com.dawn.catlovers.core.domain.usecase.ObserveBreedsUseCase
import com.dawn.catlovers.core.domain.usecase.SetFavoriteUseCase
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.uistate.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    observeBreeds: ObserveBreedsUseCase,
    private val setFavorite: SetFavoriteUseCase,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {
    val uiState: StateFlow<FavoritesUiState> = observeBreeds()
        .map { breeds ->
            FavoritesUiState(
                favoriteBreeds = breeds.filter { it.isFavorite },
                suggestedBreeds = breeds.filterNot { it.isFavorite }.take(3),
            )
        }
        .flowOn(dispatchers.default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000),
            initialValue = FavoritesUiState(),
        )

    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            setFavorite(breed.id, !breed.isFavorite)
        }
    }
}
