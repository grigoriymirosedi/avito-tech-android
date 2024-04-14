//package com.example.avito_tech_android.data.repositories
//
//import com.example.avito_tech_android.data.dao.MovieDao
//import com.example.avito_tech_android.data.entities.MovieEntity
//import com.example.avito_tech_android.domain.repositories.LocalMovieRepository
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class LocalMovieRepositoryImpl @Inject constructor(private val movieDao: MovieDao):
//    LocalMovieRepository {
//    override suspend fun getMovies(): List<MovieEntity> {
//        return movieDao.getAll()
//    }
//
//    override suspend fun getMoviesByName(name: String): MovieEntity {
//        return movieDao.findByName(name)
//    }
//
//    override suspend fun saveMovies(movies: List<MovieEntity>) {
//        return movieDao.insertAll(movies)
//    }
//}