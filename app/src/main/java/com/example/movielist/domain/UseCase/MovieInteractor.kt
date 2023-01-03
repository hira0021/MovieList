package com.example.movielist.domain.UseCase

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(val repository: IMovieRepository) : MovieUseCase {

    override suspend fun getDiscoverMovie(): Flow<DataState<DiscoverMovie>> =
        repository.getDiscoverMovie()

    override suspend fun getGenreList(): Flow<GenreList> = repository.getGenreList()

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> =
        repository.getMovieDetail(movieId)

}