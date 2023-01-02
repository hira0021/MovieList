package com.example.movielist.data.remote

import com.example.movielist.domain.Entity.DiscoverMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(@Query("api_key") apiKey: String): DiscoverMovie

}