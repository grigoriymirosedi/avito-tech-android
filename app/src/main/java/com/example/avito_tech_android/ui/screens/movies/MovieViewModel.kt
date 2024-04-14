package com.example.avito_tech_android.ui.screens.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.avito_tech_android.data.dto.toMovie
import com.example.avito_tech_android.domain.models.MovieModel
import com.example.avito_tech_android.domain.repositories.RemoteMovieRepository
import com.example.avito_tech_android.core.State
import com.example.avito_tech_android.core.Status
import com.example.avito_tech_android.data.dto.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: RemoteMovieRepository) :
    ViewModel() {

    private val _screenState = MutableStateFlow(State.empty<State<*>>())
    val screenState: StateFlow<State<*>> = _screenState

    private val _filterByYear = MutableStateFlow(false)
    val filterByYear = _filterByYear

    private val _filterByAge = MutableStateFlow(false)
    val filterByAge = _filterByAge

    private var _movies: MutableStateFlow<PagingData<Movies>> = MutableStateFlow(PagingData.empty())
    var movies = _movies

    private val sortingList = listOf('0', '0', '0')

    fun handleAgeFilter() {
        viewModelScope.launch {
            repository.fetchAllMovies(sortField=listOf("ageRating"), sortType = listOf("1") ).cachedIn(viewModelScope).collect {
                _movies.value = it
            }
        }
    }

    fun loadMovies() {
        viewModelScope.launch {
            repository.fetchAllMovies().cachedIn(viewModelScope).collect {
                _movies.value = it
            }
        }
    }
    init {
        loadMovies()
    }

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun movieSearchByName(query: String) {
        viewModelScope.launch {
            val response = repository.fetchMoviesByName(name = query).cachedIn(viewModelScope).collect {
                _movies.value = it
            }
        }
        _isSearching.value = false
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }
}