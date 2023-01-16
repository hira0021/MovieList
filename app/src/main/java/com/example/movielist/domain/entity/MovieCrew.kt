package com.example.movielist.domain.entity


import com.google.gson.annotations.SerializedName

data class MovieCrew(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("credit_id")
    val creditId: String = "",
    @SerializedName("department")
    val department: String = "",
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("job")
    val job: String = "",
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String = ""
)