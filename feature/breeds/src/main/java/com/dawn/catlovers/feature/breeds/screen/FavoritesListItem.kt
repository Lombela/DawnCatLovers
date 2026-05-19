package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R
import com.dawn.catlovers.feature.breeds.component.BreedImage
import com.dawn.catlovers.feature.breeds.component.CountryBadge

@Composable
internal fun FavoriteListItem(
    breed: CatBreed,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { onOpenBreed(breed.id) }
            .padding(horizontal = 11.dp, vertical = 9.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        BreedImage(
            imageUrl = breed.imageUrl,
            contentDescription = breed.name,
            contentScale = ContentScale.Crop,
            cornerRadius = 16.dp,
            modifier = Modifier
                .width(65.dp)
                .height(76.dp),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(top = 2.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(
                        text = breed.name,
                        color = CatLoversColors.Zeus,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    CountryBadge(countryCode = breed.countryCode)
                }
                Text(
                    text = breed.description.ifBlank { breed.temperamentPreview },
                    color = CatLoversColors.SoyaBean,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 10.5.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp),
            ) {
                FavoriteStatusChip()
                Text(
                    text = stringResource(R.string.favorites_saved_status),
                    color = CatLoversColors.SoyaBean,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 10.5.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    maxLines = 1,
                )
            }
        }
        IconButton(
            onClick = { onToggleFavorite(breed) },
            modifier = Modifier.size(36.dp),
        ) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = stringResource(R.string.content_description_remove_favorite),
                tint = CatLoversColors.Zeus,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
private fun FavoriteStatusChip(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.favorites_shortlist),
        color = CatLoversColors.BottleGreen,
        style = MaterialTheme.typography.labelMedium.copy(
            fontSize = 9.5.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.25.sp,
        ),
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(CatLoversColors.Skeptic)
            .padding(horizontal = 7.dp, vertical = 4.dp),
    )
}
