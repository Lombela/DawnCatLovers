package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed

@Composable
internal fun DetailAboutSection(breed: CatBreed) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 17.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        DetailSectionTitle(text = "About")
        Text(
            text = breed.description,
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp, lineHeight = 18.sp),
        )
    }
}

@Composable
internal fun WikipediaLink(breed: CatBreed) {
    val uriHandler = LocalUriHandler.current
    val url = breed.wikipediaUrl ?: return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 13.dp, end = 13.dp, top = 17.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CatLoversColors.Skeptic)
            .clickable { uriHandler.openUri(url) }
            .padding(horizontal = 13.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = CatLoversColors.BottleGreen,
            modifier = Modifier.size(17.dp),
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            Text(
                text = "Learn more on Wikipedia",
                color = CatLoversColors.BottleGreen,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp, lineHeight = 15.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = url.removePrefix("https://"),
                color = CatLoversColors.BottleGreen.copy(alpha = 0.75f),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Open Wikipedia",
            tint = CatLoversColors.BottleGreen,
            modifier = Modifier.size(15.dp),
        )
    }
}
