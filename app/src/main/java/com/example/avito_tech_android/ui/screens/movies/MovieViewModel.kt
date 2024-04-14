package com.example.avito_tech_android.ui.screens.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.avito_tech_android.data.dto.toMovie
import com.example.avito_tech_android.domain.models.MovieModel
import com.example.avito_tech_android.domain.repositories.RemoteMovieRepository
import com.example.avito_tech_android.core.State
import com.example.avito_tech_android.core.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _movies = MutableStateFlow(
        State(
            status = Status.LOADING,
            data = emptyList<MovieModel>(),
            message = ""
        )
    )

    private val sortingList = listOf('0','0','0')

    fun handleYearFilter() {
        filterByYear.value = !filterByYear.value
        if(filterByYear.value) {
            viewModelScope.launch {
                _screenState.emit(State.loading())
                try {
                    val movieResponse = repository.fetchAllMovies(sortField = listOf("ageRating"), sortType = listOf("1"))
                    _movies.emit(State.success(movieResponse.docs.map { it.toMovie() }))
                    _screenState.emit(State.success(null))
                } catch (e: Error) {
                    _movies.emit(State.error(msg = e.toString()))
                }
            }
        } else {
            viewModelScope.launch {
                _screenState.emit(State.loading())
                try {
                    val movieResponse = repository.fetchAllMovies(sortField = listOf("ageRating"), sortType = listOf("-1"))
                    _movies.emit(State.success(movieResponse.docs.map { it.toMovie() }))
                    _screenState.emit(State.success(null))
                } catch (e: Error) {
                    _movies.emit(State.error(msg = e.toString()))
                }
            }
        }
    }

    fun handleAgeFilter() {
        filterByAge.value = !filterByAge.value
        if(filterByAge.value) {
            viewModelScope.launch {
                _screenState.emit(State.loading())
                try {
                    val movieResponse = repository.fetchAllMovies(sortField = listOf("year"), sortType = listOf("1"))
                    _movies.emit(State.success(movieResponse.docs.map { it.toMovie() }))
                    _screenState.emit(State.success(null))
                } catch (e: Error) {
                    _movies.emit(State.error(msg = e.toString()))
                }
            }
        } else {
            viewModelScope.launch {
                _screenState.emit(State.loading())
                try {
                    val movieResponse = repository.fetchAllMovies(sortField = listOf("year"), sortType = listOf("-1"))
                    _movies.emit(State.success(movieResponse.docs.map { it.toMovie() }))
                    _screenState.emit(State.success(null))
                } catch (e: Error) {
                    _movies.emit(State.error(msg = e.toString()))
                }
            }
        }
    }

    fun filterSort(yearSort: Boolean, ageSort: Boolean) {
        viewModelScope.launch {
            if(yearSort) {
                repository.fetchAllMovies(sortField = listOf("ageRating"), sortType = listOf("1"))

            }
        }
    }

    val movies: StateFlow<State<List<MovieModel>>> = _movies

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _screenState.emit(State.loading())
            try {
                val movieResponse = repository.fetchAllMovies()
                _movies.emit(State.success(movieResponse.docs.map { it.toMovie() }))
                _screenState.emit(State.success(null))
            } catch (e: Error) {
                _movies.emit(State.error(msg = e.toString()))
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun movieSearchByName(query: String) {
        viewModelScope.launch {
            try {
                _screenState.emit(State.loading())
                val movieResponse = repository.fetchMoviesByName(name = query)
                Log.d("123123", movieResponse.toString())
                _movies.emit(State.success(movieResponse.docs.map { it.toMovie() }))
                _screenState.emit(State.success(null))
            } catch (e: Error) {
                _movies.emit(State.error(msg = e.toString()))
                _screenState.emit(State.error(null))
            } finally {
                _isSearching.value = false
            }
        }
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }


}