package com.example.movielist.domain.repository

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    suspend fun getDiscoverMovie(): Flow<DataState<DiscoverMovie>>

    suspend fun getGenreList(): Flow<GenreList>

    suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>>

}