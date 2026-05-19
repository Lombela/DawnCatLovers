package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.model.Lifestyle
import com.dawn.catlovers.feature.breeds.component.BottomNavDestination
import com.dawn.catlovers.feature.breeds.component.BottomNavigationBar
import com.dawn.catlovers.feature.breeds.uistate.FiltersUiState
import com.dawn.catlovers.feature.breeds.viewmodel.FiltersViewModel

@Composable
fun FiltersRoute(
    onBack: () -> Unit,
    onOpenBreed: (String) -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenProfile: () -> Unit,
    viewModel: FiltersViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FiltersScreen(
        uiState = uiState,
        onBack = onBack,
        onOpenBreed = onOpenBreed,
        onOpenFavorites = onOpenFavorites,
        onOpenProfile = onOpenProfile,
        onQueryChange = viewModel::setQuery,
        onSetCoatLength = viewModel::setCoatLength,
        onSetHypoallergenic = viewModel::setHypoallergenic,
        onSetMinEnergy = viewModel::setMinEnergy,
        onSetOrigin = viewModel::setOrigin,
        onToggleLifestyle = viewModel::toggleLifestyle,
        onClearAll = viewModel::clearAll,
    )
}

@Composable
private fun FiltersScreen(
    uiState: FiltersUiState,
    onBack: () -> Unit,
    onOpenBreed: (String) -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenProfile: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSetCoatLength: (CoatLength?) -> Unit,
    onSetHypoallergenic: (Boolean) -> Unit,
    onSetMinEnergy: (Int?) -> Unit,
    onSetOrigin: (String?) -> Unit,
    onToggleLifestyle: (Lifestyle) -> Unit,
    onClearAll: () -> Unit,
) {
    Scaffold(
        containerColor = CatLoversColors.Linen,
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = BottomNavDestination.Browse,
                onFavoritesClick = onOpenFavorites,
                onProfileClick = onOpenProfile,
            )
        },
        floatingActionButton = {
            ShowMatchesButton(
                count = uiState.matches.size,
                onClick = onBack,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            SearchBar(
                query = uiState.filters.query,
                onQueryChange = onQueryChange,
                onBack = onBack,
                modifier = Modifier.padding(start = 13.dp, end = 13.dp, top = 0.dp, bottom = 10.dp),
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 78.dp),
            ) {
                item {
                    ActiveFiltersSection(
                        filters = uiState.filters,
                        onClearAll = onClearAll,
                        onSetCoatLength = onSetCoatLength,
                        onSetHypoallergenic = onSetHypoallergenic,
                        onSetMinEnergy = onSetMinEnergy,
                        onSetOrigin = onSetOrigin,
                        onToggleLifestyle = onToggleLifestyle,
                    )
                }
                item {
                    FilterSection(title = "Coat length") {
                        CoatLength.entries.forEach { coat ->
                            FilterChip(
                                label = coat.label,
                                selected = uiState.filters.coatLength == coat,
                                onClick = { onSetCoatLength(if (uiState.filters.coatLength == coat) null else coat) },
                            )
                        }
                    }
                }
                item {
                    FilterSection(title = "Origin", contentHeight = 60.dp) {
                        FilterChip(
                            label = "Any",
                            selected = uiState.filters.origin == null,
                            onClick = { onSetOrigin(null) },
                        )
                        uiState.origins.forEach { origin ->
                            FilterChip(
                                label = origin,
                                selected = uiState.filters.origin == origin,
                                onClick = { onSetOrigin(origin) },
                            )
                        }
                    }
                }
                item {
                    FilterSection(title = "Lifestyle") {
                        Lifestyle.entries.forEach { lifestyle ->
                            FilterChip(
                                label = lifestyle.label,
                                selected = lifestyle in uiState.filters.lifestyles,
                                onClick = { onToggleLifestyle(lifestyle) },
                            )
                        }
                    }
                }
                item {
                    MatchesHeader(count = uiState.matches.size)
                }
                items(uiState.matches, key = CatBreed::id) { breed ->
                    BreedResultRow(
                        breed = breed,
                        onClick = { onOpenBreed(breed.id) },
                    )
                }
            }
        }
    }
}
