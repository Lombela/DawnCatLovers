package com.dawn.catlovers.core.network

class CatDataSource(
    private val service: TheCatApiService,
    private val apiKey: String? = null,
) {
    suspend fun fetchBreeds(page: Int = 0, limit: Int = 100): List<NetworkCatBreed> =
        service.getBreeds(
            apiKey = apiKey?.takeIf { it.isNotBlank() },
            page = page,
            limit = limit,
        ).mapNotNull { it.asNetworkModel() }
}

private fun CatBreedResponse.asNetworkModel(): NetworkCatBreed? {
    val id = id?.takeIf { it.isNotBlank() } ?: return null
    val name = name?.takeIf { it.isNotBlank() } ?: return null

    return NetworkCatBreed(
        id = id,
        name = name,
        origin = origin.orEmpty(),
        countryCode = countryCode.orEmpty(),
        description = description.orEmpty(),
        temperament = temperament.orEmpty()
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() },
        lifeSpan = lifeSpan.orEmpty(),
        weightMetric = weight?.metric.orEmpty(),
        imageUrl = image?.url?.takeIf { it.isNotBlank() },
        wikipediaUrl = wikipediaUrl?.takeIf { it.isNotBlank() },
        hypoallergenic = hypoallergenic == 1,
        indoor = indoor == 1,
        lap = lap == 1,
        affectionLevel = affectionLevel ?: 0,
        childFriendly = childFriendly ?: 0,
        dogFriendly = dogFriendly ?: 0,
        energyLevel = energyLevel ?: 0,
        grooming = grooming ?: 0,
        intelligence = intelligence ?: 0,
        socialNeeds = socialNeeds ?: 0,
        vocalisation = vocalisation ?: 0,
        sheddingLevel = sheddingLevel ?: 0,
        hairless = hairless == 1,
        rex = rex == 1,
    )
}
