package com.example.avito_tech_android.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val id: Int,
    val name: String,
    val type: String,
    val year: Int,
    val ageRating: Int,
    val description: String,
    val countries: String,
    val poster: PosterModel
): Parcelable
