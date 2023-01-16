package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieBelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("poster_path")
    val posterPath: String = ""
)