package com.example.movielist.data.remote

import com.example.movielist.domain.entity.DiscoverMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(@Query("api_key") apiKey: String): DiscoverMovie

}