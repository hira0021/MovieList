package com.example.movielist.data.datasource

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieDataSource {

    suspend fun getDiscoverMovieFromDataSource(): Flow<DataState<DiscoverMovie>>

    suspend fun getGenreListFromDataSource(): Flow<GenreList>

    suspend fun getMovieDetailFromDataSource(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getCreditsFromDataSource(movieId: Int): Flow<DataState<MovieCredits>>

}