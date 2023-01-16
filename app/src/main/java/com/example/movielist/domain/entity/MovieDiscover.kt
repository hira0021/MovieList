package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieDiscover(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val movieDiscoverResults: List<MovieDiscoverResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)