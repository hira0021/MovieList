package com.example.movielist.data.repository

import com.example.movielist.data.datasource.IMovieDataSource
import com.example.movielist.domain.entity.MovieDiscoverResponse
import com.example.movielist.domain.entity.MovieGenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieDataSource: IMovieDataSource): IMovieRepository {

    override suspend fun getDiscoverMovie(): Flow<DataState<MovieDiscoverResponse>> {
        return movieDataSource.getDiscoverMovieFromDataSource()
    }

    override suspend fun getGenreList(): Flow<MovieGenreList> {
        return movieDataSource.getGenreListFromDataSource()
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> {
        return movieDataSource.getMovieDetailFromDataSource(movieId)
    }

    override suspend fun getCredits(movieId: Int): Flow<DataState<MovieCredits>> {
        return movieDataSource.getCreditsFromDataSource(movieId)
    }

}