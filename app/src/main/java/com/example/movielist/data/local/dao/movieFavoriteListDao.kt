package com.example.movielist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity

@Dao
interface movieFavoriteListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavoriteList(movieFavoriteListCacheEntity: MovieFavoriteListCacheEntity): Long

    @Query("SELECT * FROM movie_list")
    suspend fun getMovieFavoriteList(): List<MovieFavoriteListCacheEntity>
}