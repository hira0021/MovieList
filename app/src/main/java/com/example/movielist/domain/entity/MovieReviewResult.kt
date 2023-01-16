package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieReviewResult(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("author_details")
    val movieReviewAuthorDetails: MovieReviewAuthorDetails = MovieReviewAuthorDetails(),
    @SerializedName("content")
    val content: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("url")
    val url: String = ""
)