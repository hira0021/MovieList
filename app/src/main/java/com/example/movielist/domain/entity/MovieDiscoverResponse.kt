package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieDiscoverResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val movieInfos: List<MovieInfo> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)