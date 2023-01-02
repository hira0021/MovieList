package com.example.movielist.data.datasource

import com.example.movielist.data.remote.MovieService
import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    val movieRetrofit: MovieService
): IMovieDataSource {
    override suspend fun getDiscoverMovieFromDataSource(): Flow<DataState<DiscoverMovie>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getDiscoverMovie("a7925cd5b088a5c25551d7bbe6f90941")
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}