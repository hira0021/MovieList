package com.example.movielist.data.remote

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    suspend fun getDiscoverMovie(@Query("api_key") apiKey: String): DiscoverMovie

    @GET("3/genre/movie/list")
    suspend fun getGenreList(@Query("api_key") apiKey: String): GenreList

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieDetail

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieCredits

}