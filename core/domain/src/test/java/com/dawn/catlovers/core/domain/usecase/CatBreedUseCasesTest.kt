package com.dawn.catlovers.core.domain.usecase

import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CatBreedUseCasesTest {
    @Test
    fun `observe breeds delegates filters to repository`() = runTest {
        val repo = FakeCatBreedsRepository(
            breeds = listOf(
                sampleBreed(id = "beng", name = "Bengal", coatLength = CoatLength.Short),
                sampleBreed(id = "mcoo", name = "Maine Coon", coatLength = CoatLength.Long),
            ),
        )

        val breeds = ObserveBreedsUseCase(repo)(BreedFilters(coatLength = CoatLength.Short)).first()

        assertEquals(listOf("beng"), breeds.map { it.id })
    }

    @Test
    fun `observe breed delegates id to repository`() = runTest {
        val repo = FakeCatBreedsRepository(breeds = listOf(sampleBreed(id = "sibe", name = "Siberian")))

        val breed = ObserveBreedUseCase(repo)("sibe").first()

        assertEquals("Siberian", breed?.name)
    }

    @Test
    fun `refresh and set favorite delegate commands to repository`() = runTest {
        val repo = FakeCatBreedsRepository()

        val refreshResult = RefreshBreedsUseCase(repo)()
        SetFavoriteUseCase(repo)("beng", true)

        assertTrue(refreshResult.isSuccess)
        assertEquals(1, repo.refreshCalls)
        assertEquals("beng" to true, repo.favoriteUpdates.single())
    }

}

private class FakeCatBreedsRepository(
    breeds: List<CatBreed> = emptyList(),
) : CatBreedsRepository {
    private val breeds = MutableStateFlow(breeds)
    var refreshCalls = 0
    val favoriteUpdates = mutableListOf<Pair<String, Boolean>>()

    override fun observeBreeds(filters: BreedFilters): Flow<List<CatBreed>> =
        breeds.map { items -> items.filter(filters::matches) }

    override fun observeBreed(id: String): Flow<CatBreed?> =
        breeds.map { items -> items.firstOrNull { it.id == id } }

    override suspend fun refresh(): Result<Unit> {
        refreshCalls += 1
        return Result.success(Unit)
    }

    override suspend fun setFavorite(id: String, favorite: Boolean) {
        favoriteUpdates += id to favorite
    }
}

private fun sampleBreed(
    id: String = "beng",
    name: String = "Bengal",
    coatLength: CoatLength = CoatLength.Short,
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
    coatLength = coatLength,
)
