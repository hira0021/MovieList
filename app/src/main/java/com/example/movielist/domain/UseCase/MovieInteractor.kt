package com.example.movielist.domain.UseCase

import com.example.movielist.domain.entity.MovieDiscoverResponse
import com.example.movielist.domain.entity.MovieGenreList
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(val repository: IMovieRepository) : MovieUseCase {

    override suspend fun getDiscoverMovie(): Flow<DataState<MovieDiscoverResponse>> =
        repository.getDiscoverMovie()

    override suspend fun getGenreList(): Flow<MovieGenreList> = repository.getGenreList()

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> =
        repository.getMovieDetail(movieId)

    override suspend fun getCredits(movieId: Int): Flow<DataState<MovieCredits>> =
        repository.getCredits(movieId)

}