package com.dawn.catlovers.core.network

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CatDataSourceTest {
    @Test
    fun `fetch breeds passes API parameters and maps valid responses`() = runTest {
        val service = FakeTheCatApiService(
            responses = listOf(
                CatBreedResponse(
                    id = "beng",
                    name = "Bengal",
                    origin = "United States",
                    countryCode = "US",
                    description = "Athletic companion",
                    temperament = "Alert, Agile, Energetic",
                    lifeSpan = "12 - 16",
                    weight = WeightResponse(metric = "3 - 7"),
                    image = BreedImageResponse(url = "https://example.com/bengal.jpg"),
                    wikipediaUrl = "https://example.com/wiki",
                    hypoallergenic = 1,
                    indoor = 0,
                    lap = 1,
                    affectionLevel = 4,
                    childFriendly = 4,
                    dogFriendly = 5,
                    energyLevel = 5,
                    grooming = 1,
                    intelligence = 5,
                    socialNeeds = 5,
                    vocalisation = 5,
                    sheddingLevel = 3,
                    hairless = 0,
                    rex = 0,
                ),
                CatBreedResponse(id = null, name = "Invalid"),
                CatBreedResponse(id = "blank", name = " "),
            ),
        )

        val breeds = CatDataSource(service = service, apiKey = "secret").fetchBreeds(page = 2, limit = 25)

        assertEquals("secret", service.lastApiKey)
        assertEquals(2, service.lastPage)
        assertEquals(25, service.lastLimit)
        assertEquals(1, breeds.size)
        assertEquals("beng", breeds.single().id)
        assertEquals(listOf("Alert", "Agile", "Energetic"), breeds.single().temperament)
        assertEquals(true, breeds.single().hypoallergenic)
        assertEquals(false, breeds.single().indoor)
        assertEquals(true, breeds.single().lap)
    }

    @Test
    fun `fetch breeds omits blank API key`() = runTest {
        val service = FakeTheCatApiService()

        CatDataSource(service = service, apiKey = " ").fetchBreeds()

        assertNull(service.lastApiKey)
    }
}

private class FakeTheCatApiService(
    private val responses: List<CatBreedResponse> = emptyList(),
) : TheCatApiService {
    var lastApiKey: String? = null
    var lastPage: Int? = null
    var lastLimit: Int? = null

    override suspend fun getBreeds(
        apiKey: String?,
        page: Int,
        limit: Int,
    ): List<CatBreedResponse> {
        lastApiKey = apiKey
        lastPage = page
        lastLimit = limit
        return responses
    }
}
