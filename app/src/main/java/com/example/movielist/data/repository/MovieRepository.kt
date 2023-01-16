package com.example.movielist.data.repository

import com.example.movielist.data.datasource.IMovieDataSource
import com.example.movielist.domain.entity.*
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieDataSource: IMovieDataSource): IMovieRepository {

    override suspend fun getDiscoverMovies(): Flow<DataState<MovieDiscover>> {
        return movieDataSource.getDiscoverMoviesFromDataSource()
    }

    override suspend fun getGenreList(): Flow<MovieGenreList> {
        return movieDataSource.getGenreListFromDataSource()
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> {
        return movieDataSource.getMovieDetailFromDataSource(movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCredits>> {
        return movieDataSource.getMovieCreditsFromDataSource(movieId)
    }

    override suspend fun getMovieReviews(movieId: Int): Flow<DataState<MovieReview>> {
        return movieDataSource.getMovieReviewFromDataSource(movieId)
    }

}