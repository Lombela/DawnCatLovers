package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R

@Composable
internal fun DetailTemperamentSection(breed: CatBreed) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 13.dp, end = 13.dp, top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        DetailSectionTitle(text = stringResource(R.string.details_temperament))
        FlowRow(
            modifier = Modifier.padding(horizontal = 7.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            breed.temperament.take(4).forEach { trait ->
                DetailChip(label = trait)
            }
        }
    }
}

@Composable
private fun DetailChip(label: String) {
    Box(
        modifier = Modifier
            .height(27.dp)
            .defaultMinSize(minWidth = 49.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CatLoversColors.TropicalBlue)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            color = CatLoversColors.Midnight,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 11.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
internal fun DetailTraitsSection(breed: CatBreed) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        DetailSectionTitle(text = stringResource(R.string.details_traits))
        Column {
            DetailTraitBar(stringResource(R.string.details_trait_affection), breed.affectionLevel)
            DetailTraitBar(stringResource(R.string.details_trait_energy), breed.energyLevel)
            DetailTraitBar(stringResource(R.string.details_trait_intelligence), breed.intelligence)
            DetailTraitBar(stringResource(R.string.details_trait_child_friendly), breed.childFriendly)
            DetailTraitBar(stringResource(R.string.details_trait_social), breed.socialNeeds)
            DetailTraitBar(stringResource(R.string.details_trait_grooming), breed.grooming)
            DetailTraitBar(stringResource(R.string.details_trait_vocalisation), breed.vocalisation)
        }
    }
}

@Composable
private fun DetailTraitBar(
    label: String,
    value: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = label,
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 13.sp),
            modifier = Modifier.width(92.dp),
            maxLines = 1,
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(7.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (index < value) CatLoversColors.StTropaz else CatLoversColors.PearlBush),
                )
            }
        }
        Text(
            text = value.toString(),
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 11.sp),
            modifier = Modifier.width(20.dp),
            maxLines = 1,
        )
    }
}

@Composable
internal fun DetailSectionTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text.uppercase(),
        color = CatLoversColors.SoyaBean,
        style = MaterialTheme.typography.labelMedium.copy(
            fontSize = 10.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.7.sp,
        ),
        modifier = modifier.fillMaxWidth(),
    )
}
