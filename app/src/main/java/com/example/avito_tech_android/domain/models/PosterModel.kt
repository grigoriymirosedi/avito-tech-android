package com.example.avito_tech_android.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PosterModel(
    val previewUrl: String?,
    val url: String?
): Parcelable
