package com.example.avito_tech_android.domain.models

data class MovieModel(
    val id: Int,
    val name: String,
    val type: String,
    val year: Int,
    val ageRating: Int,
    val description: String,
    val countries: String,
    val poster: PosterModel
)
