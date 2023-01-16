package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieReview(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val movieReviewResults: List<MovieReviewResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)