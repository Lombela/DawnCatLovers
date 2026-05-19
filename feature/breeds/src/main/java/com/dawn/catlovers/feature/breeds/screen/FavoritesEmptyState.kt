package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.feature.breeds.R

@Composable
internal fun EmptyFavoritesContent(
    suggestedBreeds: List<CatBreed>,
    onOpenBrowse: () -> Unit,
    onOpenBreed: (String) -> Unit,
    onToggleFavorite: (CatBreed) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 104.dp),
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                EmptyFavoritesImage(
                    modifier = Modifier
                        .width(180.dp)
                        .height(144.dp),
                )
                Text(
                    text = buildAnnotatedString {
                        append("No favorites ")
                        withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                            append("yet")
                        }
                    },
                    color = CatLoversColors.Zeus,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 22.sp,
                        lineHeight = 27.sp,
                    ),
                    modifier = Modifier.padding(top = 10.dp),
                )
                Text(
                    text = "Tap the heart on any breed to build your shortlist. Add notes, tag them, and compare side-by-side.",
                    color = CatLoversColors.SoyaBean,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.5.sp,
                        lineHeight = 20.sp,
                    ),
                    modifier = Modifier.padding(top = 9.dp),
                )
                ExploreBreedsButton(
                    onClick = onOpenBrowse,
                    modifier = Modifier.padding(top = 22.dp),
                )
            }
        }
        item {
            HowItWorksSection(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
            )
        }
        if (suggestedBreeds.isNotEmpty()) {
            item {
                SuggestedFavoritesSection(
                    breeds = suggestedBreeds,
                    onOpenBreed = onOpenBreed,
                    onToggleFavorite = onToggleFavorite,
                    modifier = Modifier.padding(horizontal = 14.dp),
                )
            }
        }
    }
}

@Composable
private fun EmptyFavoritesImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.empty_favourites),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )
}

@Composable
private fun ExploreBreedsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = CatLoversColors.StTropaz,
        shadowElevation = 6.dp,
        modifier = modifier
            .height(50.dp)
            .widthIn(min = 145.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = "Explore breeds",
                color = Color.White,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.5.sp, lineHeight = 13.sp),
            )
        }
    }
}

@Composable
private fun HowItWorksSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Text(
            text = "HOW IT WORKS",
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 10.5.sp,
                lineHeight = 11.sp,
                letterSpacing = 0.7.sp,
            ),
        )
        HowItWorksStep(number = "1", title = "Browse breeds", body = "Filter by coat, energy, lifestyle.")
        HowItWorksStep(number = "2", title = "Tap the heart", body = "Save any breed to your shortlist.")
        HowItWorksStep(number = "3", title = "Compare your picks", body = "Side-by-side traits to decide.")
    }
}

@Composable
private fun HowItWorksStep(
    number: String,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(CatLoversColors.SpringWood)
            .padding(horizontal = 14.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(13.dp),
    ) {
        Box(
            modifier = Modifier
                .size(29.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(CatLoversColors.TropicalBlue),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number,
                color = CatLoversColors.Midnight,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.5.sp, lineHeight = 13.sp),
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(
                text = title,
                color = CatLoversColors.Zeus,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.5.sp, lineHeight = 18.sp),
            )
            Text(
                text = body,
                color = CatLoversColors.SoyaBean,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.5.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
            )
        }
    }
}
