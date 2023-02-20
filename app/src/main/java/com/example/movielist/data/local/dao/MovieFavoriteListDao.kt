package com.example.movielist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity

@Dao
interface MovieFavoriteListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavoriteList(movieFavoriteListCacheEntity: MovieFavoriteListCacheEntity): Long

    @Query("SELECT * FROM movie_list")
    suspend fun getMovieFavoriteListCache(): List<MovieFavoriteListCacheEntity>

    @Query("SELECT * FROM movie_list WHERE id = :id LIMIT 1")
    suspend fun getMovieFavoriteCache(id: Int): MovieFavoriteListCacheEntity

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavoriteCast(movieFavoriteCastCacheEntity: MovieFavoriteCastCacheEntity): Long*/
}