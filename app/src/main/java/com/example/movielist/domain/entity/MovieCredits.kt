package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieCredits(
    @SerializedName("cast")
    val movieCast: List<MovieCast> = listOf(),
    @SerializedName("crew")
    val movieCrew: List<MovieCrew> = listOf(),
    @SerializedName("id")
    val id: Int = 0
)