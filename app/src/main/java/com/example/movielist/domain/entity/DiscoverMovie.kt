package com.example.movielist.domain.entity

import com.google.gson.annotations.SerializedName

data class DiscoverMovie(
    val page: Int = 0,
    @SerializedName("results")
    val movies: List<Movie> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)