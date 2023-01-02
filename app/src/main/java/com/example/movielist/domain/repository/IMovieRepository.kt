package com.example.movielist.domain.repository

import com.example.movielist.domain.Entity.DiscoverMovie
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getDiscoverMovie(): Flow<DataState<DiscoverMovie>>
}