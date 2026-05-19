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
import com.dawn.catlovers.feature.breeds.component.BottomNavDestination
import com.dawn.catlovers.feature.breeds.component.BottomNavigationBar
import com.dawn.catlovers.feature.breeds.uistate.FavoritesUiState
import com.dawn.catlovers.feature.breeds.viewmodel.FavoritesViewModel

@Composable
fun FavoritesRoute(
    onOpenBrowse: () -> Unit,
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoritesScreen(
        uiState = uiState,
        onOpenBrowse = onOpenBrowse,
        onOpenBreed = onOpenBreed,
        onOpenFilters = onOpenFilters,
        onToggleFavorite = viewModel::toggleFavorite,
    )
}

@Composable
private fun FavoritesScreen(
    uiState: FavoritesUiState,
    onOpenBrowse: () -> Unit,
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    Scaffold(
        containerColor = CatLoversColors.Linen,
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = BottomNavDestination.Favorites,
                onBrowseClick = onOpenBrowse,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            FavoritesHeader(
                favoriteCount = uiState.favoriteCount,
                onOpenFilters = onOpenFilters,
                onSearch = onOpenFilters,
                onShare = {},
            )
            FavoritesContent(
                uiState = uiState,
                onOpenBrowse = onOpenBrowse,
                onOpenBreed = onOpenBreed,
                onToggleFavorite = onToggleFavorite,
            )
        }
    }
}
