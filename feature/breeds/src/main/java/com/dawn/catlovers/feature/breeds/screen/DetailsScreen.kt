package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.uistate.DetailsUiState
import com.dawn.catlovers.feature.breeds.viewmodel.DetailsViewModel

@Composable
fun DetailsRoute(
    onBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(
        uiState = uiState,
        onBack = onBack,
        onToggleFavorite = viewModel::toggleFavorite,
    )
}

@Composable
private fun DetailsScreen(
    uiState: DetailsUiState,
    onBack: () -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    val breed = uiState.breed
    Scaffold(
        containerColor = CatLoversColors.Linen,
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
                breed = breed,
                onBack = onBack,
                onToggleFavorite = onToggleFavorite,
                modifier = Modifier.padding(padding),
            )
        }
    }
}
