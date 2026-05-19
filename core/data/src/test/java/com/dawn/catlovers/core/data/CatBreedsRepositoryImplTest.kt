package com.dawn.catlovers.core.data

import com.dawn.catlovers.core.database.CatBreedDao
import com.dawn.catlovers.core.database.CatBreedEntity
import com.dawn.catlovers.core.database.mapper.asEntity
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.network.BreedImageResponse
import com.dawn.catlovers.core.network.CatDataSource
import com.dawn.catlovers.core.network.CatBreedResponse
import com.dawn.catlovers.core.network.TheCatApiService
import com.dawn.catlovers.core.network.WeightResponse
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CatBreedsRepositoryImplTest {
    @Test
    fun `observe breeds emits filtered local database data`() = runTest {
        val dao = FakeCatBreedDao(
            initial = listOf(
                sampleBreed(id = "beng", name = "Bengal", hypoallergenic = true).asEntity(1L),
                sampleBreed(id = "mcoo", name = "Maine Coon", hypoallergenic = false).asEntity(1L),
            ),
        )
        val repository = repository(dao = dao)

        val breeds = repository.observeBreeds(BreedFilters(hypoallergenicOnly = true)).first()

        assertEquals(listOf("beng"), breeds.map { it.id })
    }

    @Test
    fun `refresh stores network data and preserves local favorites`() = runTest {
        val dao = FakeCatBreedDao(
            initial = listOf(sampleBreed(id = "beng", name = "Old Bengal", isFavorite = true).asEntity(1L)),
        )
        val repository = repository(
            dao = dao,
            service = FakeTheCatApiService(
                responses = listOf(networkBreedResponse(id = "beng", name = "Bengal")),
            ),
            clock = Clock.fixed(Instant.ofEpochMilli(99L), ZoneOffset.UTC),
        )

        val result = repository.refresh()
        val breed = repository.observeBreed("beng").first()

        assertTrue(result.isSuccess)
        checkNotNull(breed)
        assertEquals("Bengal", breed.name)
        assertEquals(true, breed.isFavorite)
        assertEquals(99L, dao.entities.value.single().updatedAtMillis)
    }

    @Test
    fun `set favorite updates local store`() = runTest {
        val dao = FakeCatBreedDao(initial = listOf(sampleBreed(id = "beng").asEntity(1L)))
        val repository = repository(dao = dao)

        repository.setFavorite("beng", true)

        assertEquals(true, repository.observeBreed("beng").first()?.isFavorite)
    }

    private fun repository(
        dao: FakeCatBreedDao = FakeCatBreedDao(),
        service: FakeTheCatApiService = FakeTheCatApiService(),
        clock: Clock = Clock.systemUTC(),
    ) = CatBreedsRepositoryImpl(
        dao = dao,
        dataSource = CatDataSource(service = service),
        clock = clock,
    )
}

private class FakeCatBreedDao(
    initial: List<CatBreedEntity> = emptyList(),
) : CatBreedDao {
    val entities = MutableStateFlow(initial)

    override fun observeBreeds(): Flow<List<CatBreedEntity>> = entities

    override fun observeBreed(id: String): Flow<CatBreedEntity?> =
        entities.map { items -> items.firstOrNull { it.id == id } }

    override suspend fun favoriteIds(): List<String> =
        entities.value.filter { it.isFavorite }.map { it.id }

    override suspend fun upsertBreeds(breeds: List<CatBreedEntity>) {
        val merged = entities.value.associateBy { it.id }.toMutableMap()
        breeds.forEach { merged[it.id] = it }
        entities.value = merged.values.sortedBy { it.name }
    }

    override suspend fun updateFavorite(id: String, favorite: Boolean) {
        entities.value = entities.value.map { entity ->
            if (entity.id == id) entity.copy(isFavorite = favorite) else entity
        }
    }

    override suspend fun replaceFromNetwork(breeds: List<CatBreedEntity>) {
        val favoriteIds = favoriteIds().toSet()
        upsertBreeds(breeds.map { it.copy(isFavorite = it.isFavorite || it.id in favoriteIds) })
    }
}

private class FakeTheCatApiService(
    private val responses: List<CatBreedResponse> = emptyList(),
) : TheCatApiService {
    override suspend fun getBreeds(
        apiKey: String?,
        page: Int,
        limit: Int,
    ): List<CatBreedResponse> = responses
}

private fun networkBreedResponse(
    id: String = "beng",
    name: String = "Bengal",
) = CatBreedResponse(
    id = id,
    name = name,
    origin = "United States",
    countryCode = "US",
    description = "Athletic companion",
    temperament = "Alert, Agile, Energetic",
    lifeSpan = "12 - 16",
    weight = WeightResponse(metric = "3 - 7"),
    image = BreedImageResponse(url = "https://example.com/bengal.jpg"),
    hypoallergenic = 1,
    indoor = 0,
    lap = 0,
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
)

private fun sampleBreed(
    id: String = "beng",
    name: String = "Bengal",
    hypoallergenic: Boolean = true,
    isFavorite: Boolean = false,
) = CatBreed(
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
    hypoallergenic = hypoallergenic,
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
    isFavorite = isFavorite,
)
