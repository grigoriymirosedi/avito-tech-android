package com.example.avito_tech_android.ui.screens.movies

import android.util.Log
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
import androidx.room.util.query
import com.example.avito_tech_android.core.State
import com.example.avito_tech_android.core.Status
import com.google.android.material.chip.ChipGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    modifier: Modifier,
    viewModel: MovieViewModel = hiltViewModel(),
) {

    val searchText = viewModel.searchText.collectAsState()
    val isSearching = viewModel.isSearching.collectAsState()

    var isYearFilterCheapEnabled by remember { mutableStateOf(false) }
    var isAgeFilterCheapEnabled by remember { mutableStateOf(false) }

    val filterByYear = viewModel.filterByYear.collectAsState()
    val filterByAge = viewModel.filterByAge.collectAsState()

    val screenState = viewModel.screenState.collectAsState()
    val moviesList = viewModel.movies.collectAsState()

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

        if (screenState.value.status == Status.LOADING) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(Alignment.Center)
            )
        }

        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                FilterChip(
                    selected = filterByYear.value,
                    onClick = {
                        viewModel.handleYearFilter()
                    },
                    label = { Text("Год выпуска") }
                )
                FilterChip(
                    selected = filterByAge.value,
                    onClick = {
                        viewModel.handleAgeFilter()
                    },
                    label = { Text("Возрастное ограничение") })
            }


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(moviesList.value.data ?: emptyList()) { movie ->
                    MovieCard(movieModel = movie)
                }
            }
        }

    }
}