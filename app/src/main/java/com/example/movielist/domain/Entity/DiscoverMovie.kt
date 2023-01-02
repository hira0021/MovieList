package com.example.movielist.domain.Entity

data class DiscoverMovie(
    val page: Int,
    val result: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)