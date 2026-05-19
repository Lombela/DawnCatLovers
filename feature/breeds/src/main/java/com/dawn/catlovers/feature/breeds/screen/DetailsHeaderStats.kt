package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.component.CountryBadge

@Composable
internal fun DetailTitleSection(breed: CatBreed) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 7.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CountryBadge(countryCode = breed.countryCode)
            Text(
                text = breed.origin.uppercase(),
                color = CatLoversColors.StTropaz,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    letterSpacing = 0.7.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = breed.name,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 30.sp,
                lineHeight = 33.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
internal fun DetailStatsRow(breed: CatBreed) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 13.dp, end = 13.dp, top = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        DetailStatCard("Lifespan", "${breed.lifeSpan} yrs", modifier = Modifier.weight(1f))
        DetailStatCard("Weight", "${breed.weightMetric} kg", modifier = Modifier.weight(1f))
        DetailStatCard("Coat", breed.coatLength.label, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun DetailStatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CatLoversColors.SpringWood)
            .padding(start = 10.dp, end = 10.dp, top = 11.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = label.uppercase(),
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 9.sp,
                lineHeight = 9.sp,
                letterSpacing = 0.5.sp,
                fontWeight = FontWeight.Normal,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = value,
            color = CatLoversColors.Zeus,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.5.sp, lineHeight = 17.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
