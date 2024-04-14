package com.example.avito_tech_android.domain.repositories

import com.example.avito_tech_android.data.dto.MoviesDTO

interface RemoteMovieRepository {
    suspend fun fetchAllMovies(sortField: List<String>? = null, sortType: List<String>? = null): MoviesDTO

    suspend fun fetchMoviesByName(name: String): MoviesDTO
}