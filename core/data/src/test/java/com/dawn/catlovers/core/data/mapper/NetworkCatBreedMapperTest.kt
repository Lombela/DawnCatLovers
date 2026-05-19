package com.dawn.catlovers.core.data.mapper

import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.network.NetworkCatBreed
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkCatBreedMapperTest {
    @Test
    fun `maps network breed and clamps trait scores`() {
        val breed = networkBreed(
            affectionLevel = 8,
            childFriendly = -1,
            energyLevel = 4,
            grooming = 1,
        ).asExternalModel()

        assertEquals("beng", breed.id)
        assertEquals(5, breed.affectionLevel)
        assertEquals(0, breed.childFriendly)
        assertEquals(4, breed.energyLevel)
        assertEquals(CoatLength.Short, breed.coatLength)
    }

    @Test
    fun `infers coat length from network fields`() {
        assertEquals(CoatLength.Hairless, networkBreed(hairless = true).asExternalModel().coatLength)
        assertEquals(CoatLength.Long, networkBreed(name = "Maine Coon").asExternalModel().coatLength)
        assertEquals(CoatLength.SemiLong, networkBreed(name = "Siberian").asExternalModel().coatLength)
        assertEquals(CoatLength.Long, networkBreed(name = "Unknown", grooming = 4).asExternalModel().coatLength)
    }

    private fun networkBreed(
        id: String = "beng",
        name: String = "Bengal",
        affectionLevel: Int = 4,
        childFriendly: Int = 4,
        energyLevel: Int = 5,
        grooming: Int = 1,
        hairless: Boolean = false,
    ) = NetworkCatBreed(
        id = id,
        name = name,
        origin = "United States",
        countryCode = "US",
        description = "Athletic companion",
        temperament = listOf("Alert", "Agile"),
        lifeSpan = "12 - 16",
        weightMetric = "3 - 7",
        imageUrl = null,
        wikipediaUrl = null,
        hypoallergenic = true,
        indoor = false,
        lap = false,
        affectionLevel = affectionLevel,
        childFriendly = childFriendly,
        dogFriendly = 5,
        energyLevel = energyLevel,
        grooming = grooming,
        intelligence = 5,
        socialNeeds = 5,
        vocalisation = 5,
        sheddingLevel = 3,
        hairless = hairless,
        rex = false,
    )
}
