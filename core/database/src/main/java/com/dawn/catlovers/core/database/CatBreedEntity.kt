package com.dawn.catlovers.core.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dawn.catlovers.core.model.CoatLength

@Entity(
    tableName = "cat_breeds",
    indices = [
        Index(value = ["name"]),
        Index(value = ["origin"]),
        Index(value = ["hypoallergenic"]),
        Index(value = ["coatLength"]),
    ],
)
data class CatBreedEntity(
    @PrimaryKey val id: String,
    val name: String,
    val origin: String,
    val countryCode: String,
    val description: String,
    val temperament: String,
    val lifeSpan: String,
    val weightMetric: String,
    val imageUrl: String?,
    val wikipediaUrl: String?,
    val hypoallergenic: Boolean,
    val indoor: Boolean,
    val lap: Boolean,
    val affectionLevel: Int,
    val childFriendly: Int,
    val dogFriendly: Int,
    val energyLevel: Int,
    val grooming: Int,
    val intelligence: Int,
    val socialNeeds: Int,
    val vocalisation: Int,
    val sheddingLevel: Int,
    val coatLength: CoatLength,
    val isFavorite: Boolean,
    val updatedAtMillis: Long,
)
