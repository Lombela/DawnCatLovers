package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.component.BreedImage

private val HeroHeight = 267.dp
private val SheetOverlap = 23.dp

@Composable
internal fun BreedDetailsContent(
    breed: CatBreed,
    onBack: () -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CatLoversColors.Linen),
    ) {
        BreedImage(
            imageUrl = breed.imageUrl,
            contentDescription = breed.name,
            contentScale = ContentScale.Crop,
            cornerRadius = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(HeroHeight),
        )
        DetailTopBar(
            isFavorite = breed.isFavorite,
            onBack = onBack,
            onShare = { },
            onToggleFavorite = { onToggleFavorite(breed) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp, vertical = 7.dp),
        )
        Column(
            modifier = Modifier
                .padding(top = HeroHeight - SheetOverlap)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(CatLoversColors.Linen)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 104.dp),
        ) {
            DetailSheetHandle()
            DetailTitleSection(breed = breed)
            DetailStatsRow(breed = breed)
            DetailTemperamentSection(breed = breed)
            DetailTraitsSection(breed = breed)
            DetailAboutSection(breed = breed)
            WikipediaLink(breed = breed)
        }
    }
}

@Composable
private fun DetailTopBar(
    isFavorite: Boolean,
    onBack: () -> Unit,
    onShare: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        DetailOverlayIconButton(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back",
            onClick = onBack,
            iconSize = 18.dp,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            DetailOverlayIconButton(
                imageVector = Icons.Rounded.Share,
                contentDescription = "Share",
                onClick = onShare,
                iconSize = 17.dp,
            )
            DetailOverlayIconButton(
                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = if (isFavorite) "Remove favorite" else "Add favorite",
                onClick = onToggleFavorite,
                iconSize = 18.dp,
            )
        }
    }
}
