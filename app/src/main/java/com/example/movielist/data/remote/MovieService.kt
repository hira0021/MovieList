package com.example.movielist.data.remote

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(@Query("api_key") apiKey: String): DiscoverMovie

    @GET("3/genre/movie/list")
    suspend fun getGenreList(@Query("api_key") apiKey: String) : GenreList

}