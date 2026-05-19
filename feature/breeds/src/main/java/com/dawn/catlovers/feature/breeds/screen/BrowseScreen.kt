package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.component.BottomNavigationBar
import com.dawn.catlovers.feature.breeds.uistate.BrowseUiState
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter
import com.dawn.catlovers.feature.breeds.viewmodel.BrowseViewModel

@Composable
fun BrowseRoute(
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    viewModel: BrowseViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BrowseScreen(
        uiState = uiState,
        onOpenBreed = onOpenBreed,
        onOpenFilters = onOpenFilters,
        onSelectFilter = viewModel::selectFilter,
        onToggleFavorite = viewModel::toggleFavorite,
        onRefresh = viewModel::refresh,
    )
}

@Composable
private fun BrowseScreen(
    uiState: BrowseUiState,
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    onSelectFilter: (QuickFilter) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    onRefresh: () -> Unit,
) {
    val heroBreed = uiState.heroBreed
    Scaffold(
        containerColor = CatLoversColors.Linen,
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            if (heroBreed != null) {
                BrowseSurpriseButton(onClick = { onOpenBreed(heroBreed.id) })
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            BrowseHeader(
                isRefreshing = uiState.isRefreshing,
                onOpenFilters = onOpenFilters,
                onRefresh = onRefresh,
            )
            BrowseContent(
                uiState = uiState,
                onOpenBreed = onOpenBreed,
                onSelectFilter = onSelectFilter,
                onToggleFavorite = onToggleFavorite,
                onRefresh = onRefresh,
            )
        }
    }
}
