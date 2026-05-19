package com.dawn.catlovers.core.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CatBreedTest {
    @Test
    fun `temperament preview contains first three traits`() {
        val breed = sampleBreed(temperament = listOf("Alert", "Agile", "Energetic", "Demanding"))

        assertEquals("Alert · Agile · Energetic", breed.temperamentPreview)
    }

    @Test
    fun `origin label falls back when origin is blank`() {
        assertEquals("Unknown", sampleBreed(origin = "").originLabel)
        assertEquals("Thailand", sampleBreed(origin = "Thailand").originLabel)
    }

    private fun sampleBreed(
        origin: String = "United States",
        temperament: List<String> = listOf("Alert"),
    ) = CatBreed(
        id = "beng",
        name = "Bengal",
        origin = origin,
        countryCode = "US",
        description = "Athletic companion",
        temperament = temperament,
        lifeSpan = "12 - 16",
        weightMetric = "3 - 7",
        imageUrl = null,
        wikipediaUrl = null,
        hypoallergenic = true,
        indoor = false,
        lap = false,
        affectionLevel = 4,
        childFriendly = 4,
        dogFriendly = 5,
        energyLevel = 5,
        grooming = 1,
        intelligence = 5,
        socialNeeds = 5,
        vocalisation = 5,
        sheddingLevel = 3,
        coatLength = CoatLength.Short,
    )
}
