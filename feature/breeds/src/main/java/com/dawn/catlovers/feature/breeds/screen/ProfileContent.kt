package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun ProfileSignedOutContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item { ProfileSignedOutHero() }
        item { ProfileAuthActions() }
        item { WhySignInSection() }
        item { ProfileSignedOutFooter() }
    }
}

@Composable
private fun ProfileSignedOutHero(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 13.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(CatLoversColors.PearlBush),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = null,
                tint = CatLoversColors.SoyaBean,
                modifier = Modifier.size(33.dp),
            )
        }
        Text(
            text = buildAnnotatedString {
                append("Signed ")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("out")
                }
            },
            color = CatLoversColors.Zeus,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 24.sp,
                lineHeight = 28.sp,
            ),
            modifier = Modifier.padding(top = 15.dp),
        )
        Text(
            text = "Sign in to sync your favorites and notes across devices.",
            color = CatLoversColors.SoyaBean,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.5.sp,
                lineHeight = 20.sp,
            ),
            modifier = Modifier
                .widthIn(max = 268.dp)
                .padding(top = 8.dp),
        )
    }
}

@Composable
private fun ProfileSignedOutFooter(modifier: Modifier = Modifier) {
    Text(
        text = "You can keep browsing without an account - favorites stay on this device.",
        color = CatLoversColors.SoyaBean,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelMedium.copy(
            fontSize = 10.5.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Normal,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 44.dp, end = 44.dp, top = 25.dp, bottom = 14.dp),
    )
}
