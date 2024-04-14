package com.example.remap.di

import com.example.avito_tech_android.data.repositories.RemoteMovieRepositoryImpl
import com.example.avito_tech_android.data.repositories.api.ApiInterface
import com.example.avito_tech_android.domain.repositories.RemoteMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecyclePointRepository(api: ApiInterface): RemoteMovieRepository {
        return RemoteMovieRepositoryImpl(api = api)
    }

}