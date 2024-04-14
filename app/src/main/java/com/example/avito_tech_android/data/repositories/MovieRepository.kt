package com.example.avito_tech_android.data.repositories

import com.example.avito_tech_android.data.repositories.api.ApiInterface
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: ApiInterface,
//    private val db: AppDatabase
){
}