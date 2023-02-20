package com.example.movielist.domain.UseCase

import androidx.paging.PagingData
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity
import com.example.movielist.domain.entity.*
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getDiscoverMovies(page: Int): Flow<DataState<MovieDiscover>>

    fun getPagerDiscoverMovies(query: String): Flow<PagingData<MovieDiscoverResult>>

    suspend fun getGenreList(): Flow<MovieGenreList>

    suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>>

    suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCredits>>

    suspend fun getMovieReview(movieId: Int): Flow<DataState<MovieReview>>

    suspend fun saveFavoriteMovie(movieDetail: MovieDetail)

    suspend fun getFavoriteMovieListCache(): Flow<DataState<List<MovieFavoriteListCacheEntity>>>

    suspend fun getFavoriteMovieCache(id: Int): Flow<DataState<MovieDetail>>

}