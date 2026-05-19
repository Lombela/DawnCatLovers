package com.dawn.catlovers.core.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BreedFiltersTest {
    @Test
    fun `matches query across name origin coat temperament and description`() {
        val breed = sampleBreed(
            name = "Bengal",
            origin = "United States",
            temperament = listOf("Alert", "Agile", "Energetic"),
            description = "Athletic companion",
            coatLength = CoatLength.Short,
        )

        assertTrue(BreedFilters(query = "agile").matches(breed))
        assertTrue(BreedFilters(query = "states").matches(breed))
        assertTrue(BreedFilters(query = "short").matches(breed))
        assertFalse(BreedFilters(query = "quiet").matches(breed))
    }

    @Test
    fun `combines selected filters with and semantics`() {
        val breed = sampleBreed(
            coatLength = CoatLength.Short,
            hypoallergenic = true,
            energyLevel = 5,
            indoor = true,
            childFriendly = 4,
        )

        assertTrue(
            BreedFilters(
                coatLength = CoatLength.Short,
                hypoallergenicOnly = true,
                minEnergy = 4,
                lifestyles = setOf(Lifestyle.Indoor, Lifestyle.Family),
            ).matches(breed),
        )
        assertFalse(BreedFilters(coatLength = CoatLength.Long).matches(breed))
    }

    private fun sampleBreed(
        name: String = "Siberian",
        origin: String = "Russia",
        temperament: List<String> = listOf("Curious"),
        description: String = "Friendly cat",
        coatLength: CoatLength = CoatLength.Long,
        hypoallergenic: Boolean = false,
        energyLevel: Int = 3,
        indoor: Boolean = false,
        childFriendly: Int = 3,
    ) = CatBreed(
        id = name.lowercase(),
        name = name,
        origin = origin,
        countryCode = "US",
        description = description,
        temperament = temperament,
        lifeSpan = "12 - 16",
        weightMetric = "3 - 7",
        imageUrl = null,
        wikipediaUrl = null,
        hypoallergenic = hypoallergenic,
        indoor = indoor,
        lap = false,
        affectionLevel = 4,
        childFriendly = childFriendly,
        dogFriendly = 3,
        energyLevel = energyLevel,
        grooming = 2,
        intelligence = 4,
        socialNeeds = 3,
        vocalisation = 2,
        sheddingLevel = 2,
        coatLength = coatLength,
    )
}
