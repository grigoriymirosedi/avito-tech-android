package com.example.avito_tech_android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.avito_tech_android.data.dto.Movies
import com.example.avito_tech_android.data.repositories.api.ApiInterface

class MoviePagingSource(
    private val movieApi: ApiInterface,
): PagingSource<Int, Movies>() {
    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        return try {
            val page = params.key ?: 1
            val response = movieApi.getMovies(page = page)

            LoadResult.Page(
                data = response.docs,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.docs.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}