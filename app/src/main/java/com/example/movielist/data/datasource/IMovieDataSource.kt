package com.example.movielist.data.datasource

import com.example.movielist.domain.entity.MovieDiscoverResponse
import com.example.movielist.domain.entity.MovieGenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieDataSource {

    suspend fun getDiscoverMovieFromDataSource(): Flow<DataState<MovieDiscoverResponse>>

    suspend fun getGenreListFromDataSource(): Flow<MovieGenreList>

    suspend fun getMovieDetailFromDataSource(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getCreditsFromDataSource(movieId: Int): Flow<DataState<MovieCredits>>

}