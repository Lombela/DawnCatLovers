package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.component.BottomNavigationBar
import com.dawn.catlovers.feature.breeds.component.BreedImage
import com.dawn.catlovers.feature.breeds.component.CatChip
import com.dawn.catlovers.feature.breeds.component.CountryBadge
import com.dawn.catlovers.feature.breeds.component.FavoriteButton
import com.dawn.catlovers.feature.breeds.component.GradientScrim
import com.dawn.catlovers.feature.breeds.component.IconCircleButton
import com.dawn.catlovers.feature.breeds.viewmodel.BrowseViewModel
import com.dawn.catlovers.feature.breeds.uistate.BrowseUiState
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BrowseScreen(
    uiState: BrowseUiState,
    onOpenBreed: (String) -> Unit,
    onOpenFilters: () -> Unit,
    onSelectFilter: (QuickFilter) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    onRefresh: () -> Unit,
) {
    Scaffold(
        containerColor = CatLoversColors.Linen,
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            uiState.breeds.firstOrNull()?.let { breed ->
                FloatingActionButton(
                    onClick = { onOpenBreed(breed.id) },
                    containerColor = CatLoversColors.TropicalBlue,
                    contentColor = CatLoversColors.Midnight,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Icon(Icons.Rounded.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Text("Surprise me", style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        },
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(start = 14.dp, end = 14.dp, top = 0.dp, bottom = 88.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                BrowseHeader(
                    isRefreshing = uiState.isRefreshing,
                    onOpenFilters = onOpenFilters,
                    onRefresh = onRefresh,
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                QuickFilterRow(
                    selected = uiState.selectedFilter,
                    onSelectFilter = onSelectFilter,
                )
            }
            uiState.heroBreed?.let { breed ->
                item(span = { GridItemSpan(maxLineSpan) }) {
                    HeroBreedCard(
                        breed = breed,
                        onOpenBreed = onOpenBreed,
                        onToggleFavorite = onToggleFavorite,
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "All breeds",
                        color = CatLoversColors.Zeus,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "${uiState.breeds.size} results",
                        color = CatLoversColors.SoyaBean,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
            if (uiState.breeds.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    EmptyBrowseState(
                        message = uiState.syncMessage ?: "No breeds match the current filter",
                        onRefresh = onRefresh,
                    )
                }
            } else {
                items(uiState.breeds, key = { it.id }) { breed ->
                    BreedGridCard(
                        breed = breed,
                        onOpenBreed = onOpenBreed,
                        onToggleFavorite = onToggleFavorite,
                    )
                }
            }
        }
    }
}

@Composable
private fun BrowseHeader(
    isRefreshing: Boolean,
    onOpenFilters: () -> Unit,
    onRefresh: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconCircleButton(
                imageVector = Icons.AutoMirrored.Rounded.List,
                contentDescription = "Open filters",
                onClick = onOpenFilters,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                IconCircleButton(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    onClick = onOpenFilters,
                )
                IconCircleButton(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "Refresh breeds",
                    onClick = onRefresh,
                )
            }
        }
        Column(modifier = Modifier.padding(horizontal = 6.dp)) {
            Text(
                text = "CAT LOVER",
                color = CatLoversColors.StTropaz,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = buildAnnotatedString {
                    append("Find your\n")
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("perfect")
                    }
                    append(" companion")
                },
                color = CatLoversColors.Zeus,
                style = MaterialTheme.typography.displayMedium,
            )
        }
        if (isRefreshing) {
            Row(
                modifier = Modifier.padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CircularProgressIndicator(modifier = Modifier.size(14.dp), strokeWidth = 2.dp)
                Text(
                    text = "Syncing TheCatAPI",
                    color = CatLoversColors.SoyaBean,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@Composable
private fun QuickFilterRow(
    selected: QuickFilter,
    onSelectFilter: (QuickFilter) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 2.dp),
    ) {
        items(QuickFilter.entries.size) { index ->
            val filter = QuickFilter.entries[index]
            CatChip(
                label = filter.label,
                selected = selected == filter,
                leadingIcon = if (selected == filter) Icons.Rounded.Check else null,
                onClick = { onSelectFilter(filter) },
            )
        }
    }
}

@Composable
private fun HeroBreedCard(
    breed: CatBreed,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable { onOpenBreed(breed.id) },
    ) {
        BreedImage(
            imageUrl = breed.imageUrl,
            contentDescription = breed.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        GradientScrim(modifier = Modifier.fillMaxSize())
        CatChip(
            label = "Breed of the day",
            selected = true,
            leadingIcon = Icons.Rounded.Check,
            onClick = { },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp),
        )
        FavoriteButton(
            isFavorite = breed.isFavorite,
            onClick = { onToggleFavorite(breed) },
            dark = true,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(18.dp),
        ) {
            Text(
                text = breed.name,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "${breed.originLabel} · ${breed.temperamentPreview}",
                color = Color.White.copy(alpha = 0.86f),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun BreedGridCard(
    breed: CatBreed,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CatLoversColors.SpringWood)
            .clickable { onOpenBreed(breed.id) },
    ) {
        Box {
            BreedImage(
                imageUrl = breed.imageUrl,
                contentDescription = breed.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.22f),
            )
            FavoriteButton(
                isFavorite = breed.isFavorite,
                onClick = { onToggleFavorite(breed) },
                dark = true,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(34.dp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = breed.name,
                    color = CatLoversColors.Zeus,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.size(6.dp))
                CountryBadge(countryCode = breed.countryCode)
            }
            Text(
                text = breed.temperamentPreview,
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun EmptyBrowseState(
    message: String,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CatLoversColors.SpringWood)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = message,
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.bodyMedium,
        )
        Button(
            onClick = onRefresh,
            colors = ButtonDefaults.buttonColors(containerColor = CatLoversColors.StTropaz),
            shape = RoundedCornerShape(14.dp),
        ) {
            Text("Try again")
        }
    }
}
