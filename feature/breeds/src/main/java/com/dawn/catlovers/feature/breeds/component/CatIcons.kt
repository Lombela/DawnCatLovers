package com.dawn.catlovers.feature.breeds.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val FilterSlidersIcon: ImageVector = ImageVector.Builder(
    name = "FilterSliders",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(fill = SolidColor(Color.Black)) {
        moveTo(4f, 7f)
        horizontalLineTo(10f)
        verticalLineTo(5f)
        horizontalLineTo(12f)
        verticalLineTo(11f)
        horizontalLineTo(10f)
        verticalLineTo(9f)
        horizontalLineTo(4f)
        verticalLineTo(7f)
        close()
        moveTo(14f, 7f)
        horizontalLineTo(20f)
        verticalLineTo(9f)
        horizontalLineTo(14f)
        verticalLineTo(7f)
        close()
        moveTo(4f, 15f)
        horizontalLineTo(8f)
        verticalLineTo(13f)
        horizontalLineTo(10f)
        verticalLineTo(19f)
        horizontalLineTo(8f)
        verticalLineTo(17f)
        horizontalLineTo(4f)
        verticalLineTo(15f)
        close()
        moveTo(12f, 15f)
        horizontalLineTo(20f)
        verticalLineTo(17f)
        horizontalLineTo(12f)
        verticalLineTo(15f)
        close()
        moveTo(16f, 3f)
        horizontalLineTo(18f)
        verticalLineTo(5f)
        horizontalLineTo(20f)
        verticalLineTo(7f)
        horizontalLineTo(18f)
        verticalLineTo(11f)
        horizontalLineTo(16f)
        verticalLineTo(7f)
        horizontalLineTo(14f)
        verticalLineTo(5f)
        horizontalLineTo(16f)
        verticalLineTo(3f)
        close()
    }
}.build()
