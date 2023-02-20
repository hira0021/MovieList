package com.example.movielist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//@Entity(tableName = "movie_cast")
data class MovieFavoriteCastCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "people_id")
    val peopleId: Int,

    @ColumnInfo(name = "gender")
    val gender: Int,

    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "profile_path")
    val profilePath: String,

    @ColumnInfo(name = "character")
    val character: String

)
