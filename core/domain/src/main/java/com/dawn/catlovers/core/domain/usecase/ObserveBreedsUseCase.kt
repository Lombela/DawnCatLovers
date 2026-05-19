package com.dawn.catlovers.core.domain.usecase

import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveBreedsUseCase @Inject constructor(
    private val repository: CatBreedsRepository,
) {
    operator fun invoke(filters: BreedFilters = BreedFilters()): Flow<List<CatBreed>> =
        repository.observeBreeds(filters)
}
