package com.example.avito_tech_android.data.repositories.api

import com.example.avito_tech_android.BuildConfig
import com.example.avito_tech_android.data.dto.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("x-api-key: " + BuildConfig.AVITO_TECH_TOKEN)
    @GET("movie")
    suspend fun getMovies(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("sortField") sortField: List<String>? = null,
        @Query("sortType") sortType: List<String>? = null,
    ): MoviesDTO

    @Headers("x-api-key: " + BuildConfig.AVITO_TECH_TOKEN)
    @GET("movie/search")
    suspend fun getMovieByName(
        @Query("page") page: Int = 1,
        @Query("query") movieName: String,
        @Header("Authorization") token: String = BuildConfig.AVITO_TECH_TOKEN
    ): MoviesDTO
}