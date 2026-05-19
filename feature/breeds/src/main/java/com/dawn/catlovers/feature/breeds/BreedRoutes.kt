package com.dawn.catlovers.feature.breeds

object BreedRoutes {
    const val Browse = "browse"
    const val Filters = "filters"
    const val Details = "details/{breedId}"

    fun details(breedId: String): String = "details/$breedId"
}
