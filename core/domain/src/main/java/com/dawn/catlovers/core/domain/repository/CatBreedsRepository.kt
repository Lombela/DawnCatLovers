package com.dawn.catlovers.core.domain.repository

import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedsRepository {
    fun observeBreeds(filters: BreedFilters = BreedFilters()): Flow<List<CatBreed>>
    fun observeBreed(id: String): Flow<CatBreed?>
    suspend fun refresh(): Result<Unit>
    suspend fun setFavorite(id: String, favorite: Boolean)
}
