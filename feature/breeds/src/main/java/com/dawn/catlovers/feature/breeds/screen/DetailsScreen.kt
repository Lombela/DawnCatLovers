package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.uistate.DetailsUiState
import com.dawn.catlovers.feature.breeds.viewmodel.DetailsViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsRoute(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        uiState = uiState,
        onBack = onBack,
        onToggleFavorite = viewModel::toggleFavorite,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun DetailsScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: DetailsUiState,
    onBack: () -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    val breed = uiState.breed
    Scaffold(
        containerColor = CatLoversColors.Linen,
        contentWindowInsets = WindowInsets(0.dp),
        floatingActionButton = {
            if (breed != null) {
                DetailSaveButton(
                    isFavorite = breed.isFavorite,
                    onClick = { onToggleFavorite(breed) },
                )
            }
        },
    ) { padding ->
        if (breed == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                Text("Breed not found", color = CatLoversColors.SoyaBean)
            }
        } else {
            BreedDetailsContent(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                breed = breed,
                onBack = onBack,
                onToggleFavorite = onToggleFavorite,
                modifier = Modifier.padding(padding),
            )
        }
    }
}
