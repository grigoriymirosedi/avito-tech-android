package com.example.avito_tech_android.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.avito_tech_android.data.dto.Movies
import com.example.avito_tech_android.data.dto.MoviesDTO
import com.example.avito_tech_android.data.paging.MovieByNamePagingSource
import com.example.avito_tech_android.data.paging.MoviePagingSource
import com.example.avito_tech_android.data.repositories.api.ApiInterface
import com.example.avito_tech_android.domain.repositories.RemoteMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteMovieRepositoryImpl @Inject constructor(private val api: ApiInterface) :
    RemoteMovieRepository {
    override fun fetchAllMovies(
        sortField: List<String>?,
        sortType: List<String>?,
    ): Flow<PagingData<Movies>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieApi = api)
            }
        ).flow
    }

    override fun fetchMoviesByName(name: String): Flow<PagingData<Movies>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MovieByNamePagingSource(movieApi = api, name = name)
            }
        ).flow
    }
}