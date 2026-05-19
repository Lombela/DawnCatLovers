package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.component.BreedImage
import com.dawn.catlovers.feature.breeds.component.CountryBadge
import com.dawn.catlovers.feature.breeds.component.FavoriteButton

@Composable
internal fun BrowseSectionHeader(resultCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "All breeds",
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, lineHeight = 20.sp),
        )
        Text(
            text = "$resultCount results",
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                lineHeight = 11.sp,
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun BrowseBreedRow(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    breeds: List<CatBreed>,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(163.dp)
            .padding(horizontal = 13.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        BreedGridCard(
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            breed = breeds[0],
            onOpenBreed = onOpenBreed,
            onToggleFavorite = onToggleFavorite,
            modifier = Modifier.weight(1f),
        )
        if (breeds.size > 1) {
            BreedGridCard(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                breed = breeds[1],
                onOpenBreed = onOpenBreed,
                onToggleFavorite = onToggleFavorite,
                modifier = Modifier.weight(1f),
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun BreedGridCard(
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

    Column(
        modifier = modifier
            .fillMaxHeight()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp), clip = false)
            .clip(RoundedCornerShape(20.dp))
            .background(CatLoversColors.SpringWood)
            .clickable { onOpenBreed(breed.id) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(109.dp),
        ) {
            BreedImage(
                imageUrl = breed.imageUrl,
                contentDescription = breed.name,
                contentScale = ContentScale.Crop,
                cornerRadius = 0.dp,
                modifier = sharedImageModifier,
            )
            if (breed.hypoallergenic) {
                HypoallergenicBadge(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(7.dp),
                )
            }
            FavoriteButton(
                isFavorite = breed.isFavorite,
                onClick = { onToggleFavorite(breed) },
                dark = true,
                buttonSize = 27.dp,
                iconSize = 13.dp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 7.dp, bottom = 7.dp),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = breed.name,
                    color = CatLoversColors.Zeus,
                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.5.sp, lineHeight = 17.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(6.dp))
                CountryBadge(countryCode = breed.countryCode)
            }
            Text(
                text = breed.temperament.take(2).joinToString(" · "),
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun HypoallergenicBadge(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White.copy(alpha = 0.92f))
            .padding(horizontal = 7.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "HYPOALLERGENIC",
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 8.sp,
                lineHeight = 8.sp,
                letterSpacing = 0.3.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            maxLines = 1,
        )
    }
}

@Composable
internal fun EmptyBrowseState(
    message: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
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
