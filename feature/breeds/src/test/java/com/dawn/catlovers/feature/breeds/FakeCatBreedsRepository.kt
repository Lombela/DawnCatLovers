package com.dawn.catlovers.feature.breeds

import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.model.CoatLength
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeCatBreedsRepository(
    breeds: List<CatBreed> = emptyList(),
    var refreshResult: Result<Unit> = Result.success(Unit),
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
        return refreshResult
    }

    override suspend fun setFavorite(id: String, favorite: Boolean) {
        favoriteUpdates += id to favorite
        breeds.update { items ->
            items.map { breed ->
                if (breed.id == id) breed.copy(isFavorite = favorite) else breed
            }
        }
    }
}

fun testBreed(
    id: String = "beng",
    name: String = "Bengal",
    origin: String = "United States",
    countryCode: String = "US",
    temperament: List<String> = listOf("Alert", "Agile", "Energetic"),
    coatLength: CoatLength = CoatLength.Short,
    hypoallergenic: Boolean = true,
    indoor: Boolean = false,
    childFriendly: Int = 4,
    energyLevel: Int = 5,
    socialNeeds: Int = 5,
    lap: Boolean = false,
    isFavorite: Boolean = false,
    description: String = "Short-haired athletic companion",
) = CatBreed(
    id = id,
    name = name,
    origin = origin,
    countryCode = countryCode,
    description = description,
    temperament = temperament,
    lifeSpan = "12 - 16",
    weightMetric = "3 - 7",
    imageUrl = null,
    wikipediaUrl = null,
    hypoallergenic = hypoallergenic,
    indoor = indoor,
    lap = lap,
    affectionLevel = 4,
    childFriendly = childFriendly,
    dogFriendly = 5,
    energyLevel = energyLevel,
    grooming = 1,
    intelligence = 5,
    socialNeeds = socialNeeds,
    vocalisation = 5,
    sheddingLevel = 3,
    coatLength = coatLength,
    isFavorite = isFavorite,
)
