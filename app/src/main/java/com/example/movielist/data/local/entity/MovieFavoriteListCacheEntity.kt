package com.example.movielist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list")
data class MovieFavoriteListCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "adult")
    val adult: Boolean = false,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",

    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int> = listOf(),

    @ColumnInfo(name = "original_language")
    val originalLanguage: String = "",

    @ColumnInfo(name = "original_title")
    val originalTitle: String = "",

    @ColumnInfo(name = "overview")
    val overview: String = "",

    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",

    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "video")
    val video: Boolean = false,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0
)