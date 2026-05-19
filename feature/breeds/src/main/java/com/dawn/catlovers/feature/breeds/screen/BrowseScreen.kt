package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import com.dawn.catlovers.feature.breeds.uistate.BrowseUiState
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter
import com.dawn.catlovers.feature.breeds.viewmodel.BrowseViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BrowseRoute(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenProfile: () -> Unit,
    viewModel: BrowseViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    BrowseScreen(
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        uiState = uiState,
        onOpenBreed = onOpenBreed,
        onOpenFilters = onOpenFilters,
        onOpenFavorites = onOpenFavorites,
        onOpenProfile = onOpenProfile,
        onSelectFilter = viewModel::selectFilter,
        onToggleFavorite = viewModel::toggleFavorite,
        onRefresh = viewModel::refresh,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun BrowseScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: BrowseUiState,
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenProfile: () -> Unit,
    onSelectFilter: (QuickFilter) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    onRefresh: () -> Unit,
) {
    val heroBreed = uiState.heroBreed
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
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                uiState = uiState,
                onOpenBreed = onOpenBreed,
                onSelectFilter = onSelectFilter,
                onToggleFavorite = onToggleFavorite,
                onRefresh = onRefresh,
            )
        }
    }
}
