package com.dawn.catlovers.feature.breeds.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.catlovers.core.domain.usecase.ObserveBreedUseCase
import com.dawn.catlovers.core.domain.usecase.SetFavoriteUseCase
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.uistate.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    observeBreed: ObserveBreedUseCase,
    private val setFavorite: SetFavoriteUseCase,
) : ViewModel() {
    private val breedId: String = checkNotNull(savedStateHandle["breedId"])

    val uiState: StateFlow<DetailsUiState> = observeBreed(breedId)
        .map { breed -> DetailsUiState(breed = breed) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000),
            initialValue = DetailsUiState(),
        )

    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            setFavorite(breed.id, !breed.isFavorite)
        }
    }
}