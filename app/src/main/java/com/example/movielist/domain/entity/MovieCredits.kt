package com.example.movielist.domain.entity

data class MovieCredits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 0
)