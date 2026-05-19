package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.uistate.BrowseUiState
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter

@Composable
internal fun BrowseContent(
    uiState: BrowseUiState,
    onOpenBreed: (String) -> Unit,
    onSelectFilter: (QuickFilter) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    onRefresh: () -> Unit,
) {
    val heroBreed = uiState.heroBreed
    val gridBreeds = if (heroBreed == null) uiState.breeds else uiState.breeds.drop(1)
    val breedRows = gridBreeds.chunked(2)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 104.dp),
    ) {
        item {
            QuickFilterRow(
                selected = uiState.selectedFilter,
                onSelectFilter = onSelectFilter,
            )
        }
        if (heroBreed != null) {
            item {
                HeroBreedCard(
                    breed = heroBreed,
                    onOpenBreed = onOpenBreed,
                    onToggleFavorite = onToggleFavorite,
                    modifier = Modifier
                        .padding(horizontal = 13.dp)
                        .height(184.dp),
                )
            }
        }
        item {
            BrowseSectionHeader(resultCount = uiState.breeds.size)
        }
        if (uiState.breeds.isEmpty()) {
            item {
                EmptyBrowseState(
                    message = uiState.syncMessage ?: "No breeds match the current filter",
                    onRefresh = onRefresh,
                    modifier = Modifier.padding(horizontal = 13.dp),
                )
            }
        } else {
            itemsIndexed(
                items = breedRows,
                key = { _, row -> row.joinToString(separator = "-") { it.id } },
            ) { index, row ->
                BrowseBreedRow(
                    breeds = row,
                    onOpenBreed = onOpenBreed,
                    onToggleFavorite = onToggleFavorite,
                    modifier = Modifier.padding(bottom = if (index == breedRows.lastIndex) 20.dp else 43.dp),
                )
            }
        }
    }
}
