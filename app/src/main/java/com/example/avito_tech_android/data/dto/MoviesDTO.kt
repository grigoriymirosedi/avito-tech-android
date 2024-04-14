package com.example.avito_tech_android.data.dto

data class MoviesDTO(
    val docs: List<Movies>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)