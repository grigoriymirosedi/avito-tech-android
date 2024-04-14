package com.example.avito_tech_android.domain.repositories

import androidx.paging.PagingData
import com.example.avito_tech_android.data.dto.Movies
import com.example.avito_tech_android.data.dto.MoviesDTO
import kotlinx.coroutines.flow.Flow

interface RemoteMovieRepository {
    fun fetchAllMovies(sortField: List<String>? = null, sortType: List<String>? = null): Flow<PagingData<Movies>>

    fun fetchMoviesByName(name: String): Flow<PagingData<Movies>>
}