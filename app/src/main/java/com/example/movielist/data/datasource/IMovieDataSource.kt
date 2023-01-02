package com.example.movielist.data.datasource

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface IMovieDataSource {

    suspend fun getDiscoverMovieFromDataSource(): Flow<DataState<DiscoverMovie>>

}