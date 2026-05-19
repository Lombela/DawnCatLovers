package com.dawn.catlovers.feature.breeds

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.model.Lifestyle

@StringRes
internal fun CoatLength.labelResId(): Int = when (this) {
    CoatLength.Hairless -> R.string.coat_length_hairless
    CoatLength.Short -> R.string.coat_length_short
    CoatLength.SemiLong -> R.string.coat_length_semi_long
    CoatLength.Long -> R.string.coat_length_long
}

@Composable
internal fun CoatLength.labelText(): String = stringResource(labelResId())

@StringRes
internal fun Lifestyle.labelResId(): Int = when (this) {
    Lifestyle.Indoor -> R.string.lifestyle_indoor
    Lifestyle.Outdoor -> R.string.lifestyle_outdoor
    Lifestyle.Apartment -> R.string.lifestyle_apartment
    Lifestyle.Family -> R.string.lifestyle_family
    Lifestyle.Single -> R.string.lifestyle_single
}

@Composable
internal fun Lifestyle.labelText(): String = stringResource(labelResId())

@Composable
internal fun CatBreed.originLabelText(): String = origin.ifBlank {
    stringResource(R.string.breed_origin_unknown)
}
