package com.dawn.catlovers.feature.breeds.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dawn.catlovers.core.designsystem.CatLoversColors

@Composable
internal fun ProfileAuthActions(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 34.dp, end = 34.dp, top = 25.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        DemoDisabledPrimaryButton(text = "Sign in")
        DemoDisabledOutlinedButton(text = "Create an account")
        ContinueWithDivider(modifier = Modifier.padding(top = 9.dp))
        DemoDisabledGoogleButton(modifier = Modifier.padding(top = 3.dp))
    }
}

@Composable
private fun DemoDisabledPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = CatLoversColors.StTropaz.copy(alpha = 0.72f),
        shadowElevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .semantics {
                role = Role.Button
                disabled()
            },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                color = Color.White.copy(alpha = 0.88f),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 12.5.sp,
                    lineHeight = 13.sp,
                ),
            )
        }
    }
}

@Composable
private fun DemoDisabledOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent,
        border = BorderStroke(1.dp, CatLoversColors.Cloudy),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .semantics {
                role = Role.Button
                disabled()
            },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                color = CatLoversColors.StTropaz.copy(alpha = 0.68f),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 12.5.sp,
                    lineHeight = 13.sp,
                ),
            )
        }
    }
}

@Composable
private fun ContinueWithDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        HorizontalDivider(
            color = CatLoversColors.Swirl,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "OR CONTINUE WITH",
            color = CatLoversColors.SoyaBean,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 10.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Normal,
            ),
        )
        HorizontalDivider(
            color = CatLoversColors.Swirl,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun DemoDisabledGoogleButton(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = CatLoversColors.SpringWood,
        border = BorderStroke(1.dp, CatLoversColors.Swirl),
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .semantics {
                role = Role.Button
                disabled()
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(CatLoversColors.FlushMahogany),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "G",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
            Text(
                text = "Google",
                color = CatLoversColors.Zeus.copy(alpha = 0.72f),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 12.5.sp,
                    lineHeight = 13.sp,
                ),
            )
        }
    }
}
