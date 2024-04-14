package com.example.avito_tech_android.ui.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.avito_tech_android.ui.screens.movies.MovieDetails
import com.example.avito_tech_android.ui.screens.movies.MoviesScreen
import com.example.avito_tech_android.ui.utils.Screens

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            startDestination = Screens.Movies.route,
            navController = navController,
        ) {
            composable(Screens.Movies.route) {
                MoviesScreen(navController = navController)
            }
            composable(Screens.MovieDetails.route) {
                MovieDetails(navController = navController)
            }
        }
    }

}