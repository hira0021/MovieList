package com.example.movielist.data.datasource

import android.util.Log
import com.example.movielist.data.local.mapper.MovieFavoriteListCacheMapper
import com.example.movielist.data.local.dao.MovieFavoriteListDao
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity
import com.example.movielist.data.remote.MovieService
import com.example.movielist.domain.entity.*
import com.example.movielist.util.Const
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    val movieRetrofit: MovieService,
    val movieFavoriteListDao: MovieFavoriteListDao,
    val movieFavoriteListCacheMapper: MovieFavoriteListCacheMapper
) {

    suspend fun getDiscoverMoviesFromDataSource(page: Int): Flow<DataState<MovieDiscover>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getDiscoverMovie(page, Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getGenreListFromDataSource(): Flow<MovieGenreList> = flow {
        try {
            val data = movieRetrofit.getGenreList(Const.API_KEY)
            emit(data)
        } catch (e: Exception) {
            Log.e("MovieDataSource", e.toString())
        }
    }

    suspend fun getMovieDetailFromDataSource(movieId: Int) = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getMovieDetail(movieId, Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getMovieCreditsFromDataSource(movieId: Int): Flow<DataState<MovieCredits>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getCredits(movieId, Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getMovieReviewFromDataSource(movieId: Int): Flow<DataState<MovieReview>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieRetrofit.getMovieReview(movieId, Const.API_KEY)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun saveMovieToFavorite(movieDetail: MovieDetail) {
        val movie = movieFavoriteListCacheMapper.mapToEntity(movieDetail)
        movieFavoriteListDao.insertMovieFavoriteList(movie)
    }

    suspend fun getFavoriteMovieListCache(): Flow<DataState<List<MovieFavoriteListCacheEntity>>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieFavoriteListDao.getMovieFavoriteListCache()
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getFavoriteMovieCache(id: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val data = movieFavoriteListCacheMapper.mapFromEntity(movieFavoriteListDao.getMovieFavoriteCache(id))
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}