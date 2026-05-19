package com.dawn.catlovers.core.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TheCatApiService {
    @GET("v1/breeds")
    suspend fun getBreeds(
        @Header("x-api-key") apiKey: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<CatBreedResponse>
}
