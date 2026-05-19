package com.dawn.catlovers.core.data.mapper

import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.network.CatBreedResource

fun CatBreedResource.asExternalModel(): CatBreed = CatBreed(
    id = id,
    name = name,
    origin = origin,
    countryCode = countryCode,
    description = description,
    temperament = temperament,
    lifeSpan = lifeSpan,
    weightMetric = weightMetric,
    imageUrl = imageUrl,
    wikipediaUrl = wikipediaUrl,
    hypoallergenic = hypoallergenic,
    indoor = indoor,
    lap = lap,
    affectionLevel = affectionLevel.coerceIn(0, 5),
    childFriendly = childFriendly.coerceIn(0, 5),
    dogFriendly = dogFriendly.coerceIn(0, 5),
    energyLevel = energyLevel.coerceIn(0, 5),
    grooming = grooming.coerceIn(0, 5),
    intelligence = intelligence.coerceIn(0, 5),
    socialNeeds = socialNeeds.coerceIn(0, 5),
    vocalisation = vocalisation.coerceIn(0, 5),
    sheddingLevel = sheddingLevel.coerceIn(0, 5),
    coatLength = inferCoatLength(name = name, hairless = hairless, rex = rex, grooming = grooming),
)

private fun inferCoatLength(
    name: String,
    hairless: Boolean,
    rex: Boolean,
    grooming: Int,
): CoatLength = when {
    hairless -> CoatLength.Hairless
    rex -> CoatLength.Short
    name in setOf("Maine Coon", "Persian", "Norwegian Forest Cat", "Ragdoll") -> CoatLength.Long
    name in setOf("Siberian", "Balinese", "Birman", "Turkish Angora") -> CoatLength.SemiLong
    grooming >= 4 -> CoatLength.Long
    else -> CoatLength.Short
}
