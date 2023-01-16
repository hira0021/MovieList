package com.example.movielist.domain.repository

import com.example.movielist.domain.entity.MovieDiscoverResponse
import com.example.movielist.domain.entity.MovieGenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    suspend fun getDiscoverMovie(): Flow<DataState<MovieDiscoverResponse>>

    suspend fun getGenreList(): Flow<MovieGenreList>

    suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getCredits(movieId: Int): Flow<DataState<MovieCredits>>

}