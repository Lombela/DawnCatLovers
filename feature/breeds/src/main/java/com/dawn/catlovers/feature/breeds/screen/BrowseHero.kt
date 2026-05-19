package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.component.BreedImage
import com.dawn.catlovers.feature.breeds.component.FavoriteButton
import com.dawn.catlovers.feature.breeds.component.GradientScrim
import com.dawn.catlovers.feature.breeds.originLabelText

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun HeroBreedCard(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    breed: CatBreed,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sharedImageModifier = with(sharedTransitionScope) {
        Modifier
            .sharedElement(
                sharedContentState = rememberSharedContentState(
                    key = BreedImageSharedElementKey(breed.id),
                ),
                animatedVisibilityScope = animatedVisibilityScope,
            )
            .fillMaxSize()
    }

    Box(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(28.dp), clip = false)
            .clip(RoundedCornerShape(28.dp))
            .clickable { onOpenBreed(breed.id) },
    ) {
        BreedImage(
            imageUrl = breed.imageUrl,
            contentDescription = breed.name,
            contentScale = ContentScale.Crop,
            cornerRadius = 0.dp,
            modifier = sharedImageModifier,
        )
        GradientScrim(modifier = Modifier.fillMaxSize())
        BreedOfDayChip(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp),
        )
        FavoriteButton(
            isFavorite = breed.isFavorite,
            onClick = { onToggleFavorite(breed) },
            dark = true,
            buttonSize = 33.dp,
            iconSize = 18.dp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 15.dp, end = 15.dp, bottom = 14.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = breed.name,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 22.sp,
                    lineHeight = 25.sp,
                    letterSpacing = 0.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${breed.originLabelText()} · ${breed.temperamentPreview}",
                color = Color.White.copy(alpha = 0.86f),
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 15.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun BreedOfDayChip(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White.copy(alpha = 0.92f))
            .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp))
            .padding(horizontal = 9.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = CatLoversColors.Zeus,
            modifier = Modifier.size(12.dp),
        )
        Text(
            text = stringResource(R.string.browse_breed_of_the_day),
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 9.sp,
                lineHeight = 9.sp,
                letterSpacing = 0.5.sp,
            ),
            maxLines = 1,
        )
    }
}
