package com.example.movielist.data.remote

import com.example.movielist.domain.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie/")
    suspend fun getDiscoverMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): MovieDiscover

    @GET("3/genre/movie/list")
    suspend fun getGenreList(@Query("api_key") apiKey: String): MovieGenreList

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

    @GET("3/movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieReview

}