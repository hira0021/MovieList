package com.example.movielist.domain.repository

import com.example.movielist.domain.entity.*
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    suspend fun getDiscoverMovies(): Flow<DataState<MovieDiscover>>

    suspend fun getGenreList(): Flow<MovieGenreList>

    suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCredits>>

    suspend fun getMovieReviews(movieId: Int): Flow<DataState<MovieReview>>

}