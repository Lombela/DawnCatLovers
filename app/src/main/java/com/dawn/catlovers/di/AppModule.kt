package com.dawn.catlovers.di

import android.content.Context
import androidx.room.Room
import com.dawn.catlovers.BuildConfig
import com.dawn.catlovers.core.data.CatBreedsRepositoryImpl
import com.dawn.catlovers.core.database.CatBreedDao
import com.dawn.catlovers.core.database.CatLoversDatabase
import com.dawn.catlovers.core.domain.CoroutineDispatchers
import com.dawn.catlovers.core.domain.repository.CatBreedsRepository
import com.dawn.catlovers.core.network.CatDataSource
import com.dawn.catlovers.core.network.TheCatApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CatLoversDatabase =
        Room.databaseBuilder(
            context,
            CatLoversDatabase::class.java,
            "cat-lovers.db",
        ).build()

    @Provides
    fun provideCatBreedDao(database: CatLoversDatabase): CatBreedDao = database.catBreedDao()

    @Provides
    @Singleton
    fun provideCoroutineDispatchers(): CoroutineDispatchers = CoroutineDispatchers()

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 20L * 1024L * 1024L))
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideTheCatApiService(retrofit: Retrofit): TheCatApiService =
        retrofit.create(TheCatApiService::class.java)

    @Provides
    @Singleton
    fun provideCatApiClient(service: TheCatApiService): CatDataSource =
        CatDataSource(
            service = service,
            apiKey = BuildConfig.THE_CAT_API_KEY,
        )

    @Provides
    @Singleton
    fun provideCatBreedsRepository(
        dao: CatBreedDao,
        apiClient: CatDataSource,
        dispatchers: CoroutineDispatchers,
    ): CatBreedsRepository = CatBreedsRepositoryImpl(
        dao = dao,
        dataSource = apiClient,
        dispatchers = dispatchers,
    )
}
