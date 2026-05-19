package com.dawn.catlovers.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColors: ColorScheme = lightColorScheme(
    primary = CatLoversColors.StTropaz,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = CatLoversColors.TropicalBlue,
    onPrimaryContainer = CatLoversColors.Midnight,
    secondary = CatLoversColors.SelectedChip,
    onSecondary = CatLoversColors.Zeus,
    background = CatLoversColors.Linen,
    onBackground = CatLoversColors.Zeus,
    surface = CatLoversColors.Linen,
    onSurface = CatLoversColors.Zeus,
    surfaceVariant = CatLoversColors.SpringWood,
    onSurfaceVariant = CatLoversColors.SoyaBean,
    outline = CatLoversColors.Swirl,
)

val CatLoversTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 30.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Normal,
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Medium,
    ),
)

private val CatShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
)

@Composable
fun CatLoversTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = CatLoversTypography,
        shapes = CatShapes,
        content = content,
    )
}
