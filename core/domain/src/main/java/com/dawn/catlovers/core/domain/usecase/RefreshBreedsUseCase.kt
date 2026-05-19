package com.dawn.catlovers.core.domain.usecase

import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import javax.inject.Inject

class RefreshBreedsUseCase @Inject constructor(
    private val repository: CatBreedsRepository,
) {
    suspend operator fun invoke(): Result<Unit> = repository.refresh()
}
