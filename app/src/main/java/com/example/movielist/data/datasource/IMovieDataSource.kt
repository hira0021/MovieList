package com.example.movielist.data.datasource

import com.example.movielist.domain.entity.*
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieDataSource {

    suspend fun getDiscoverMoviesFromDataSource(): Flow<DataState<MovieDiscover>>

    suspend fun getGenreListFromDataSource(): Flow<MovieGenreList>

    suspend fun getMovieDetailFromDataSource(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getMovieCreditsFromDataSource(movieId: Int): Flow<DataState<MovieCredits>>

    suspend fun getMovieReviewFromDataSource(movieId: Int): Flow<DataState<MovieReview>>

}