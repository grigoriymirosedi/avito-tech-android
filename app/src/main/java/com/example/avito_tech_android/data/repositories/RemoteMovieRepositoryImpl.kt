package com.example.avito_tech_android.data.repositories

import com.example.avito_tech_android.data.dto.MoviesDTO
import com.example.avito_tech_android.data.repositories.api.ApiInterface
import com.example.avito_tech_android.domain.repositories.RemoteMovieRepository
import javax.inject.Inject

class RemoteMovieRepositoryImpl @Inject constructor(private val api: ApiInterface): RemoteMovieRepository {
    override suspend fun fetchAllMovies(
        sortField: List<String>?,
        sortType: List<String>?,
    ): MoviesDTO {
        return api.getMovies(sortField = sortField, sortType = sortType)
    }

    override suspend fun fetchMoviesByName(name: String): MoviesDTO {
        return api.getMovieByName(movieName = name)
    }
}