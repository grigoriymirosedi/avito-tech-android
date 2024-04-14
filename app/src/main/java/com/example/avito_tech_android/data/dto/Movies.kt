package com.example.avito_tech_android.data.dto

import com.example.avito_tech_android.domain.models.MovieModel
import com.example.avito_tech_android.domain.models.PosterModel

data class Movies(
    val ageRating: Int,
    val alternativeName: String,
    val audience: List<Audience>,
    val backdrop: Backdrop,
    val budget: Budget,
    val countries: List<Country>?,
    val createdAt: String,
    val description: String?,
    val enName: String,
    val externalId: ExternalId,
    val facts: List<Fact>,
    val fees: Fees,
    val genres: List<Genre>,
    val id: Int,
    val isSeries: Boolean,
    val lists: List<String>,
    val logo: Logo,
    val movieLength: Int,
    val name: String?,
    val names: List<Name>,
    val networks: Networks,
    val persons: List<Person>,
    val poster: Poster?,
    val premiere: Premiere,
    val rating: Rating,
    val ratingMpaa: String,
    val releaseYears: List<ReleaseYear>,
    val reviewInfo: ReviewInfo,
    val seasonsInfo: List<SeasonsInfo>,
    val sequelsAndPrequels: List<SequelsAndPrequel>,
    val seriesLength: Int,
    val shortDescription: String,
    val similarMovies: List<SimilarMovy>,
    val slogan: String,
    val status: String,
    val type: String,
    val typeNumber: Int,
    val updatedAt: String,
    val videos: Videos,
    val votes: Votes,
    val year: Int
)

fun Movies.toMovie(): MovieModel = MovieModel(
    id = id,
    name = name ?: "Без названия",
    type = type,
    year = year,
    ageRating = ageRating,
    description = description ?: "",
    poster = poster?.toPosterModel() ?: PosterModel("" ,""),
    countries = if (countries?.isEmpty() == true) "Неизвестно" else countries?.first()?.name ?: "Неизвестно"
)

fun Poster.toPosterModel(): PosterModel = PosterModel(
    previewUrl = previewUrl ?: "",
    url = url ?: ""
)