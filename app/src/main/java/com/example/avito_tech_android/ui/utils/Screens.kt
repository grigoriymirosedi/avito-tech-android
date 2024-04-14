package com.example.avito_tech_android.ui.utils

sealed class Screens(val route : String, val localizedName: String? = null) {
    object Movies : Screens("movies_list", localizedName = "MoviesList")
    object MovieDetails : Screens("movie_details", localizedName = "MovieDetails")
}