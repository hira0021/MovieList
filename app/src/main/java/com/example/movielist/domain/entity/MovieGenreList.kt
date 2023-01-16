package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieGenreList(
    @SerializedName("genres")
    val movieGenres: List<MovieGenre> = listOf()
)