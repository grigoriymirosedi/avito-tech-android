//package com.example.avito_tech_android.data.dao
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.avito_tech_android.data.entities.MovieEntity
//
//@Dao
//interface MovieDao {
//    @Query("SELECT * FROM movies")
//    fun getAll(): List<MovieEntity>
//
//    @Query("SELECT * FROM movies WHERE name = :name")
//    fun findByName(name: String): MovieEntity
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(movie: MovieEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(movies: List<MovieEntity>)
//
//    @Delete
//    fun delete(movie: MovieEntity)
//}