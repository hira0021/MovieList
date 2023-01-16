package com.example.movielist.domain.UseCase

import com.example.movielist.domain.entity.*
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(val repository: IMovieRepository) : MovieUseCase {

    override suspend fun getDiscoverMovies(): Flow<DataState<MovieDiscover>> =
        repository.getDiscoverMovies()

    override suspend fun getGenreList(): Flow<MovieGenreList> = repository.getGenreList()

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> =
        repository.getMovieDetail(movieId)

    override suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCredits>> =
        repository.getMovieCredits(movieId)

    override suspend fun getMovieReview(movieId: Int): Flow<DataState<MovieReview>> =
        repository.getMovieReviews(movieId)

}