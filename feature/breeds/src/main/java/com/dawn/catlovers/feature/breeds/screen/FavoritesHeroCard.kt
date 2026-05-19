package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.component.BreedImage
import com.dawn.catlovers.feature.breeds.component.FavoriteButton
import com.dawn.catlovers.feature.breeds.component.FilterSlidersIcon
import com.dawn.catlovers.feature.breeds.component.GradientScrim

@Composable
internal fun FavoriteHeroCard(
    breed: CatBreed,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(28.dp),
        color = CatLoversColors.SpringWood,
        shadowElevation = 3.dp,
        modifier = modifier,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { onOpenBreed(breed.id) },
            ) {
                BreedImage(
                    imageUrl = breed.imageUrl,
                    contentDescription = breed.name,
                    contentScale = ContentScale.Crop,
                    cornerRadius = 0.dp,
                    modifier = Modifier.fillMaxSize(),
                )
                GradientScrim(modifier = Modifier.fillMaxSize())
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(11.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    FavoriteHeroChip(text = stringResource(R.string.favorites_pinned), dark = true, showPin = true)
                    FavoriteHeroChip(text = stringResource(R.string.favorites_top_match))
                }
                FavoriteButton(
                    isFavorite = breed.isFavorite,
                    onClick = { onToggleFavorite(breed) },
                    dark = true,
                    buttonSize = 36.dp,
                    iconSize = 20.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(11.dp),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = breed.name,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 24.sp,
                            lineHeight = 27.sp,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = stringResource(
                            R.string.favorites_saved_with_traits,
                            breed.temperament.take(2).joinToString(" · "),
                        ),
                        color = Color.White.copy(alpha = 0.88f),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 11.5.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            FavoriteHeroNote(breed = breed)
            FavoriteHeroActions(onView = { onOpenBreed(breed.id) })
        }
    }
}

@Composable
private fun FavoriteHeroChip(
    text: String,
    dark: Boolean = false,
    showPin: Boolean = false,
) {
    Row(
        modifier = Modifier
            .height(25.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (dark) CatLoversColors.StTropaz else Color.White.copy(alpha = 0.92f))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        if (showPin) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(12.dp),
            )
        }
        Text(
            text = text,
            color = if (dark) Color.White else CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 10.sp,
                lineHeight = 10.sp,
                letterSpacing = if (dark) 0.4.sp else 0.sp,
            ),
            maxLines = 1,
        )
    }
}

@Composable
private fun FavoriteHeroNote(
    breed: CatBreed,
    modifier: Modifier = Modifier,
) {
    val noteTrait = breed.temperament.firstOrNull()?.lowercase()
        ?: stringResource(R.string.favorites_note_fallback)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp)
            .background(CatLoversColors.Linen)
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(CatLoversColors.Skeptic),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.favorites_you),
                color = CatLoversColors.BottleGreen,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 9.5.sp,
                    lineHeight = 10.sp,
                    letterSpacing = 0.3.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
        Text(
            text = stringResource(R.string.favorites_match_note, breed.name, noteTrait),
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.5.sp,
                lineHeight = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun FavoriteHeroActions(onView: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(43.dp)
            .background(CatLoversColors.Linen),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FavoriteHeroAction(
            label = stringResource(R.string.favorites_action_compare),
            icon = FilterSlidersIcon,
            onClick = {},
            modifier = Modifier.weight(1f),
        )
        ActionDivider()
        FavoriteHeroAction(
            label = stringResource(R.string.favorites_action_share),
            icon = Icons.Rounded.Share,
            onClick = {},
            modifier = Modifier.weight(1f),
        )
        ActionDivider()
        FavoriteHeroAction(
            label = stringResource(R.string.favorites_action_view),
            icon = Icons.AutoMirrored.Rounded.ArrowForward,
            onClick = onView,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun FavoriteHeroAction(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CatLoversColors.Zeus,
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = label,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp, lineHeight = 12.sp),
        )
    }
}

@Composable
private fun ActionDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(CatLoversColors.Swirl),
    )
}
