package com.dawn.catlovers.core.model

data class BreedFilters(
    val query: String = "",
    val coatLength: CoatLength? = null,
    val origin: String? = null,
    val hypoallergenicOnly: Boolean = false,
    val minEnergy: Int? = null,
    val lifestyles: Set<Lifestyle> = emptySet(),
) {
    val activeCount: Int
        get() {
            var count = lifestyles.size
            if (coatLength != null) count++
            if (hypoallergenicOnly) count++
            if (minEnergy != null) count++
            if (!origin.isNullOrBlank()) count++
            return count
        }

    fun matches(breed: CatBreed): Boolean {
        val normalizedQuery = query.normalizedSearchText()
        val matchesQuery = normalizedQuery.isBlank() ||
            breed.name.normalizedSearchText().contains(normalizedQuery) ||
            breed.origin.normalizedSearchText().contains(normalizedQuery) ||
            breed.coatLength.name.normalizedSearchText().contains(normalizedQuery) ||
            breed.temperament.any { it.normalizedSearchText().contains(normalizedQuery) } ||
            breed.description.normalizedSearchText().contains(normalizedQuery)

        val matchesCoat = coatLength == null || breed.coatLength == coatLength
        val matchesOrigin = origin.isNullOrBlank() || breed.origin.equals(origin, ignoreCase = true)
        val matchesHypoallergenic = !hypoallergenicOnly || breed.hypoallergenic
        val matchesEnergy = minEnergy == null || breed.energyLevel >= minEnergy
        val matchesLifestyle = lifestyles.isEmpty() || lifestyles.all { breed.matchesLifestyle(it) }

        return matchesQuery &&
            matchesCoat &&
            matchesOrigin &&
            matchesHypoallergenic &&
            matchesEnergy &&
            matchesLifestyle
    }

    private fun CatBreed.matchesLifestyle(lifestyle: Lifestyle): Boolean = when (lifestyle) {
        Lifestyle.Indoor -> indoor
        Lifestyle.Outdoor -> energyLevel >= 4
        Lifestyle.Apartment -> energyLevel <= 3 || indoor
        Lifestyle.Family -> childFriendly >= 4
        Lifestyle.Single -> socialNeeds <= 3 || lap
    }
}

private fun String.normalizedSearchText(): String = replace(Regex("(?<=[a-z])(?=[A-Z])"), " ")
    .replace(Regex("[^A-Za-z0-9]+"), " ")
    .trim()
    .lowercase()
