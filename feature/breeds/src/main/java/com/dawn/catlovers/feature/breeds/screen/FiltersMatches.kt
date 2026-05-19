package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
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
internal fun MatchesHeader(count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 19.dp, bottom = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(R.string.filters_matches),
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp, lineHeight = 20.sp),
        )
        Text(
            text = pluralStringResource(R.plurals.breed_count, count, count),
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 15.sp),
        )
    }
}

@Composable
internal fun BreedResultRow(
    breed: CatBreed,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        BreedImage(
            imageUrl = breed.imageUrl,
            contentDescription = breed.name,
            modifier = Modifier.size(47.dp),
            contentScale = ContentScale.Crop,
            cornerRadius = 14.dp,
        )
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp),
            ) {
                Text(
                    text = breed.name,
                    color = CatLoversColors.Zeus,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.5.sp, lineHeight = 18.5.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                CountryBadge(countryCode = breed.countryCode)
            }
            Text(
                text = breed.temperamentPreview,
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Icon(
            imageVector = Icons.Rounded.FavoriteBorder,
            contentDescription = null,
            tint = CatLoversColors.Zeus,
            modifier = Modifier.size(18.dp),
        )
    }
}

@Composable
internal fun ShowMatchesButton(
    count: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = CatLoversColors.StTropaz),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(horizontal = 17.dp),
        modifier = Modifier.height(47.dp),
    ) {
        Text(
            text = stringResource(R.string.filters_show),
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp, lineHeight = 12.sp),
        )
        Spacer(Modifier.width(7.dp))
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp, lineHeight = 12.sp),
        )
        Spacer(Modifier.width(7.dp))
        Text(
            text = pluralStringResource(R.plurals.cat_count, count),
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp, lineHeight = 12.sp),
        )
    }
}
