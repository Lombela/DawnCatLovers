package com.dawn.catlovers.core.network

import com.squareup.moshi.Json

data class CatBreedResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "origin") val origin: String? = null,
    @Json(name = "country_code") val countryCode: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "temperament") val temperament: String? = null,
    @Json(name = "life_span") val lifeSpan: String? = null,
    @Json(name = "weight") val weight: WeightResponse? = null,
    @Json(name = "image") val image: BreedImageResponse? = null,
    @Json(name = "wikipedia_url") val wikipediaUrl: String? = null,
    @Json(name = "hypoallergenic") val hypoallergenic: Int? = null,
    @Json(name = "indoor") val indoor: Int? = null,
    @Json(name = "lap") val lap: Int? = null,
    @Json(name = "affection_level") val affectionLevel: Int? = null,
    @Json(name = "child_friendly") val childFriendly: Int? = null,
    @Json(name = "dog_friendly") val dogFriendly: Int? = null,
    @Json(name = "energy_level") val energyLevel: Int? = null,
    @Json(name = "grooming") val grooming: Int? = null,
    @Json(name = "intelligence") val intelligence: Int? = null,
    @Json(name = "social_needs") val socialNeeds: Int? = null,
    @Json(name = "vocalisation") val vocalisation: Int? = null,
    @Json(name = "shedding_level") val sheddingLevel: Int? = null,
    @Json(name = "hairless") val hairless: Int? = null,
    @Json(name = "rex") val rex: Int? = null,
)

data class WeightResponse(
    @Json(name = "metric") val metric: String? = null,
)

data class BreedImageResponse(
    @Json(name = "url") val url: String? = null,
)
