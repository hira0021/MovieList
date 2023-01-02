package com.example.movielist.domain.UseCase

import com.example.movielist.domain.Entity.DiscoverMovie
import com.example.movielist.domain.Entity.Movie
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getDiscoverMovie(): Flow<DataState<DiscoverMovie>>
}