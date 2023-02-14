package com.example.movielist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movielist.data.local.dao.movieFavoriteListDao
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity

@Database(entities = [MovieFavoriteListCacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieFavoriteListDao(): movieFavoriteListDao

    companion object {
        val DATABASE_NAME: String = "movie_db"
    }
}