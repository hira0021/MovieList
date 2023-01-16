package com.example.movielist.data.datasource

import android.util.Log
import com.example.movielist.data.remote.MovieService
import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.util.Const
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    val movieRetrofit: MovieService
) : IMovieDataSource {

    override suspend fun getDiscoverMovieFromDataSource(): Flow<DataState<DiscoverMovie>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getDiscoverMovie(Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun getGenreListFromDataSource(): Flow<GenreList> = flow {
        try {
            val data = movieRetrofit.getGenreList(Const.API_KEY)
            emit(data)
        } catch (e: Exception) {
            Log.e("MovieDataSource", e.toString())
        }
    }

    override suspend fun getMovieDetailFromDataSource(movieId: Int) = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getMovieDetail(movieId, Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun getCreditsFromDataSource(movieId: Int): Flow<DataState<MovieCredits>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getCredits(movieId, Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}