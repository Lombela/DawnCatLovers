package com.dawn.catlovers.core.domain.usecase

import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.model.CatBreed
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveBreedUseCase @Inject constructor(
    private val repository: CatBreedsRepository,
) {
    operator fun invoke(id: String): Flow<CatBreed?> = repository.observeBreed(id)
}
