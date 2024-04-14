package com.example.avito_tech_android.ui.screens.movies

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.avito_tech_android.R
import com.example.avito_tech_android.domain.models.MovieModel

@Composable
fun MovieDetails(navController: NavController) {
    val movieModel =
        navController.previousBackStackEntry?.savedStateHandle?.get<MovieModel>("movieModel")

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().aspectRatio(4f / 3f),
            model = movieModel!!.poster.url,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            text = movieModel!!.name,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            text = "Год выпуска: " + movieModel!!.description.toString(),
            color = Color.LightGray,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                text = "Год выпуска: " + movieModel!!.year.toString(),
                color = Color.LightGray,
            )
            Text(
                fontSize = 16.sp,
                text = movieModel!!.ageRating.toString() + "+",
                color = Color.LightGray,
                textAlign = TextAlign.End
            )
        }
    }
}