package com.dawn.catlovers.core.data

import com.dawn.catlovers.core.database.CatBreedDao
import com.dawn.catlovers.core.database.mapper.asEntity
import com.dawn.catlovers.core.database.mapper.asExternalModel
import com.dawn.catlovers.core.data.mapper.asExternalModel as asNetworkExternalModel
import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.network.CatDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Clock

class CatBreedsRepositoryImpl(
    private val dao: CatBreedDao,
    private val dataSource: CatDataSource,
    private val clock: Clock = Clock.systemUTC(),
) : CatBreedsRepository {
    override fun observeBreeds(filters: BreedFilters): Flow<List<CatBreed>> =
        dao.observeBreeds()
            .map { entities ->
                entities
                    .map { it.asExternalModel() }
                    .filter { filters.matches(it) }
            }

    override fun observeBreed(id: String): Flow<CatBreed?> =
        dao.observeBreed(id).map { it?.asExternalModel() }

    override suspend fun refresh(): Result<Unit> = runCatching {
        val now = clock.millis()
        val breeds = dataSource.fetchBreeds().map { it.asNetworkExternalModel() }
        if (breeds.isNotEmpty()) {
            dao.replaceFromNetwork(breeds.map { it.asEntity(now) })
        }
    }

    override suspend fun setFavorite(id: String, favorite: Boolean) {
        dao.updateFavorite(id, favorite)
    }
}
