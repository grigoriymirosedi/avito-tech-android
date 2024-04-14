package com.example.avito_tech_android.ui.screens.movies

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.room.util.query
import com.example.avito_tech_android.core.State
import com.example.avito_tech_android.core.Status
import com.example.avito_tech_android.data.dto.toMovie
import com.example.avito_tech_android.ui.utils.Screens
import com.google.android.material.chip.ChipGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {

    val searchText = viewModel.searchText.collectAsState()
    val isSearching = viewModel.isSearching.collectAsState()

    val filterByYear = viewModel.filterByYear.collectAsState()
    val filterByAge = viewModel.filterByAge.collectAsState()

    val moviesList = viewModel.movies.collectAsLazyPagingItems()

    Scaffold(topBar = {
        SearchBar(
            query = searchText.value,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = viewModel::movieSearchByName,
            active = isSearching.value,
            onActiveChange = { viewModel.onToogleSearch() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {}
    }) {
        Column(modifier = Modifier.padding(it)) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(
                    items = moviesList, key = { it.id }
                ) { movie ->
                    MovieCard(
                        movieModel = movie!!.toMovie(),
                        modifier = Modifier.clickable {
                            val movieArg = movie.toMovie()
                            val bundle = Bundle().apply{
                                putParcelable("movieModel", movieArg)
                            }
                            Log.d("Bundle:" , bundle.toString())
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("movieModel", movie.toMovie())
                            }
                            navController.navigate(Screens.MovieDetails.route)
                        }
                    )
                }

                moviesList.apply {
                    when (loadState.refresh) { //FIRST LOAD
                        is LoadState.Error -> {
                            //TODO Error Item
                            //state.error to get error message
                        }

                        is LoadState.Loading -> { // Loading UI
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillParentMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        text = "Refresh Loading"
                                    )

                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp)
                                            .wrapContentSize(Alignment.Center)
                                    )
                                }
                            }
                        }

                        else -> {}
                    }

                    when (loadState.append) { // Pagination
                        is LoadState.Error -> {
                            //TODO Pagination Error Item
                            //state.error to get error message
                        }

                        is LoadState.Loading -> { // Pagination Loading UI
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(text = "Pagination Loading")

                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp)
                                            .wrapContentSize(Alignment.Center)
                                    )
                                }
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}