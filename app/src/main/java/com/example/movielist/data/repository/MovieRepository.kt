package com.example.movielist.data.repository

import com.example.movielist.data.datasource.IMovieDataSource
import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieDataSource: IMovieDataSource): IMovieRepository {

    override suspend fun getDiscoverMovie(): Flow<DataState<DiscoverMovie>> {
        return movieDataSource.getDiscoverMovieFromDataSource()
    }

    override suspend fun getGenreList(): Flow<GenreList> {
        return movieDataSource.getGenreListFromDataSource()
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> {
        return movieDataSource.getMovieDetail(movieId)
    }

}