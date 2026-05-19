package com.dawn.catlovers.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {
    @Query("SELECT * FROM cat_breeds ORDER BY name COLLATE NOCASE ASC")
    fun observeBreeds(): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM cat_breeds WHERE id = :id")
    fun observeBreed(id: String): Flow<CatBreedEntity?>

    @Query("SELECT id FROM cat_breeds WHERE isFavorite = 1")
    suspend fun favoriteIds(): List<String>

    @Upsert
    suspend fun upsertBreeds(breeds: List<CatBreedEntity>)

    @Query("UPDATE cat_breeds SET isFavorite = :favorite WHERE id = :id")
    suspend fun updateFavorite(id: String, favorite: Boolean)

    @Transaction
    suspend fun replaceFromNetwork(breeds: List<CatBreedEntity>) {
        val favoriteIds = favoriteIds().toSet()
        upsertBreeds(breeds.map { it.copy(isFavorite = it.isFavorite || it.id in favoriteIds) })
    }
}
