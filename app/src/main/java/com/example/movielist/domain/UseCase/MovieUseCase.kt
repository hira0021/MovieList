package com.example.movielist.domain.UseCase

import com.example.movielist.domain.entity.*
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getDiscoverMovies(): Flow<DataState<MovieDiscover>>

    suspend fun getGenreList(): Flow<MovieGenreList>

    suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCredits>>

    suspend fun getMovieReview(movieId: Int): Flow<DataState<MovieReview>>

}