package com.dawn.catlovers.core.data

import com.dawn.catlovers.core.database.CatBreedDao
import com.dawn.catlovers.core.database.mapper.asEntity
import com.dawn.catlovers.core.database.mapper.asExternalModel
import com.dawn.catlovers.core.data.mapper.asExternalModel as asNetworkExternalModel
import com.dawn.catlovers.core.domain.CoroutineDispatchers
import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.model.BreedFilters
import com.dawn.catlovers.core.model.CatBreed
import com.dawn.catlovers.core.network.CatDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Clock

class CatBreedsRepositoryImpl(
    private val dao: CatBreedDao,
    private val dataSource: CatDataSource,
    private val dispatchers: CoroutineDispatchers = CoroutineDispatchers(),
    private val clock: Clock = Clock.systemUTC(),
) : CatBreedsRepository {
    override fun observeBreeds(filters: BreedFilters): Flow<List<CatBreed>> =
        dao.observeBreeds()
            .flowOn(dispatchers.io)
            .map { entities ->
                withContext(dispatchers.default) {
                    entities
                        .map { it.asExternalModel() }
                        .filter { filters.matches(it) }
                }
            }

    override fun observeBreed(id: String): Flow<CatBreed?> =
        dao.observeBreed(id)
            .flowOn(dispatchers.io)
            .map { entity ->
                withContext(dispatchers.default) {
                    entity?.asExternalModel()
                }
            }

    override suspend fun refresh(): Result<Unit> = withContext(dispatchers.io) {
        runCatching {
            val now = clock.millis()
            val networkBreeds = dataSource.fetchBreeds()
            val breeds = withContext(dispatchers.default) {
                networkBreeds.map { it.asNetworkExternalModel() }
            }
            if (breeds.isNotEmpty()) {
                val entities = withContext(dispatchers.default) {
                    breeds.map { it.asEntity(now) }
                }
                dao.replaceFromNetwork(entities)
            }
        }
    }

    override suspend fun setFavorite(id: String, favorite: Boolean) {
        withContext(dispatchers.io) {
            dao.updateFavorite(id, favorite)
        }
    }
}
