//package com.example.avito_tech_android.data.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.avito_tech_android.data.dao.MovieDao
//import com.example.avito_tech_android.data.entities.MovieEntity
//
//@Database(entities = [MovieEntity::class], version = 1)
//abstract class AppDatabase: RoomDatabase() {
//
//    abstract fun movieDao(): MovieDao
//
//    companion object {
//
//        private const val DB_NAME = "movie-db"
//
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(context: Context): AppDatabase {
//            return Room.databaseBuilder(
//                context,
//                AppDatabase::class.java, DB_NAME
//            ).build()
//        }
//    }
//}