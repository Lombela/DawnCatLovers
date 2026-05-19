package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.component.BreedImage
import com.dawn.catlovers.feature.breeds.component.FavoriteButton

@Composable
internal fun SuggestedFavoritesSection(
    breeds: List<CatBreed>,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "TRY THESE TO START",
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.5.sp,
                    lineHeight = 11.sp,
                    letterSpacing = 0.7.sp,
                ),
            )
            Text(
                text = "Popular picks",
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.5.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Normal,
                ),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val visibleBreeds = breeds.take(3)
            visibleBreeds.forEach { breed ->
                SuggestedBreedCard(
                    breed = breed,
                    onOpenBreed = onOpenBreed,
                    onToggleFavorite = onToggleFavorite,
                    modifier = Modifier.weight(1f),
                )
            }
            repeat(3 - visibleBreeds.size) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SuggestedBreedCard(
    breed: CatBreed,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(18.dp))
            .background(CatLoversColors.SpringWood)
            .clickable { onOpenBreed(breed.id) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(81.dp),
        ) {
            BreedImage(
                imageUrl = breed.imageUrl,
                contentDescription = breed.name,
                contentScale = ContentScale.Crop,
                cornerRadius = 0.dp,
                modifier = Modifier.fillMaxSize(),
            )
            FavoriteButton(
                isFavorite = breed.isFavorite,
                onClick = { onToggleFavorite(breed) },
                dark = true,
                buttonSize = 25.dp,
                iconSize = 13.dp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 4.dp, bottom = 4.dp),
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 7.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            Text(
                text = breed.name,
                color = CatLoversColors.Zeus,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.5.sp, lineHeight = 14.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = breed.temperament.firstOrNull() ?: breed.originLabel,
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 9.5.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
