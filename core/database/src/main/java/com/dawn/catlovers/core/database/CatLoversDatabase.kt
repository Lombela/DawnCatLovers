package com.dawn.catlovers.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CatBreedEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class CatLoversDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao
}
