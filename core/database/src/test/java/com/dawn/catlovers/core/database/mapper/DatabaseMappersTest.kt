package com.dawn.catlovers.core.database.mapper

import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import org.junit.Assert.assertEquals
import org.junit.Test

class DatabaseMappersTest {
    @Test
    fun `maps external breed to entity and back`() {
        val breed = CatBreed(
            id = "sibe",
            name = "Siberian",
            origin = "Russia",
            countryCode = "RU",
            description = "Curious family companion",
            temperament = listOf("Curious", "Intelligent", "Loyal"),
            lifeSpan = "12 - 15",
            weightMetric = "4 - 8",
            imageUrl = "https://example.com/siberian.jpg",
            wikipediaUrl = "https://example.com/wiki",
            hypoallergenic = true,
            indoor = false,
            lap = true,
            affectionLevel = 5,
            childFriendly = 4,
            dogFriendly = 5,
            energyLevel = 5,
            grooming = 2,
            intelligence = 5,
            socialNeeds = 4,
            vocalisation = 3,
            sheddingLevel = 3,
            coatLength = CoatLength.SemiLong,
            isFavorite = true,
        )

        val entity = breed.asEntity(updatedAtMillis = 42L)
        val mappedBreed = entity.asExternalModel()

        assertEquals("Curious|Intelligent|Loyal", entity.temperament)
        assertEquals(42L, entity.updatedAtMillis)
        assertEquals(breed, mappedBreed)
    }
}
