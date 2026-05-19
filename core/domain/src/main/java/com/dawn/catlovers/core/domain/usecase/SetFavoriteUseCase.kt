package com.dawn.catlovers.core.domain.usecase

import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(
    private val repository: CatBreedsRepository,
) {
    suspend operator fun invoke(id: String, favorite: Boolean) {
        repository.setFavorite(id, favorite)
    }
}
