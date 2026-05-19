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
        get() = activeLabels.size

    val activeLabels: List<String>
        get() = buildList {
            if (coatLength != null) add("${coatLength.label} coat")
            if (hypoallergenicOnly) add("Hypoallergenic")
            if (minEnergy != null) add("Energy ${minEnergy}+")
            if (!origin.isNullOrBlank()) add(origin)
            addAll(lifestyles.map { it.label })
        }

    fun matches(breed: CatBreed): Boolean {
        val normalizedQuery = query.trim()
        val matchesQuery = normalizedQuery.isBlank() ||
            breed.name.contains(normalizedQuery, ignoreCase = true) ||
            breed.origin.contains(normalizedQuery, ignoreCase = true) ||
            breed.coatLength.label.contains(normalizedQuery, ignoreCase = true) ||
            breed.temperament.any { it.contains(normalizedQuery, ignoreCase = true) } ||
            breed.description.contains(normalizedQuery, ignoreCase = true)

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
