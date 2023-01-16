package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieReviewAuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("username")
    val username: String = ""
)